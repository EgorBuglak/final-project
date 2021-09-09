package by.buglak.fitflow

import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import by.buglak.fitflow.ui.training.CommonInfo
import by.buglak.fitflow.ui.training.common.DBHelper
import kotlinx.android.synthetic.main.activity_main.*
import java.math.RoundingMode
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(CommonInfo.instance.visible) {
            afterTrainingTemporary.visibility = View.VISIBLE
        } else {
            afterTrainingTemporary.visibility = View.INVISIBLE
        }
        var millis = CommonInfo.instance.timeTemp
        var hours = TimeUnit.MILLISECONDS.toHours(millis)
        millis -= TimeUnit.HOURS.toMillis(hours)
        var minutes = TimeUnit.MILLISECONDS.toMinutes(millis)
        millis -= TimeUnit.MINUTES.toMillis(minutes)
        var seconds = TimeUnit.MILLISECONDS.toSeconds(millis)
        timeValue.text = "${hours}hr ${minutes}min ${seconds}sec"
        var kkal = 0.00
        var dbHelper = DBHelper(this.applicationContext)
        var db1 = dbHelper.writableDatabase
        var c = db1.query("userDB", null, null, null, null, null, null)
        if (c.moveToFirst()) {
            var weight: Int
            var weightMult: Double
            var genderMult: Double
            var type = c.getString(c.getColumnIndex("weightSystem"))
            var gender = c.getString(c.getColumnIndex("gender"))

            if (type.toLowerCase() == "kg") {
                weight = c.getInt(c.getColumnIndex("weightKG"))
                weightMult = 1.0
            } else {
                weight = c.getInt(c.getColumnIndex("weightPD"))
                weightMult = 2.2
            }

            if (gender.toLowerCase() == "male") {
                CommonInfo.instance.full_body = R.drawable.full_body
                CommonInfo.instance.situps = R.drawable.situps
                CommonInfo.instance.pushups = R.drawable.pushups
                CommonInfo.instance.squats = R.drawable.squats
                CommonInfo.instance.pullups = R.drawable.pullups
                genderMult = 10.5
            } else {
                CommonInfo.instance.full_body = R.drawable.full_body_fem
                CommonInfo.instance.situps = R.drawable.sit_ups_fem
                CommonInfo.instance.pushups = R.drawable.push_ups_fem
                CommonInfo.instance.squats = R.drawable.squats_fem
                CommonInfo.instance.pullups = R.drawable.pull_ups_fem
                genderMult = 12.2
            }

            kkal = weight/weightMult/genderMult*TimeUnit.MILLISECONDS.toSeconds(CommonInfo.instance.timeTemp)/60

        } else {
            kkal = 65/11.35*TimeUnit.MILLISECONDS.toSeconds(CommonInfo.instance.timeTemp)/60
        }
        c.close()
        var kkal1 = kkal.toBigDecimal().setScale(2,RoundingMode.UP).toDouble()
        kkalValue.text = kkal1.toString()
tempResultsBtn.setOnClickListener{
    CommonInfo.instance.visible = false
    afterTrainingTemporary.visibility = View.INVISIBLE
}
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_training, R.id.navigation_reports, R.id.navigation_me
            )
        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        nav_view.selectedItemId = CommonInfo.instance.itemId
    }

    override fun onStart() {
        super.onStart()

        CommonInfo.instance.itemId = R.id.navigation_training
    }

}
