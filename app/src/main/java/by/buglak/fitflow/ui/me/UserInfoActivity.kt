package by.buglak.fitflow.ui.me

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import by.buglak.fitflow.MainActivity
import by.buglak.fitflow.R
import by.buglak.fitflow.ui.training.CommonInfo
import by.buglak.fitflow.ui.training.common.DBHelper
import kotlinx.android.synthetic.main.activity_user_info.*
import kotlinx.android.synthetic.main.activity_user_info.view.*

class UserInfoActivity : AppCompatActivity() {
    var gender = ""
    var metric = ""
    var weight = 0.0
    var height = 0.0
    var cv = ContentValues()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)
        userInfoBackBtn.setOnClickListener {
            val intent = Intent(this.applicationContext, MainActivity::class.java)
            startActivity(intent)
        }
        val dbHelper = DBHelper(this.applicationContext)
        val db1 = dbHelper.writableDatabase

        var c = db1.query("userDB", null, null, null, null, null, null)
        if (c.moveToFirst()) {
            var gender = c.getString(c.getColumnIndex("gender"))
            genderToggle.isChecked = gender != "male"
            var metric = c.getString(c.getColumnIndex("weightSystem"))
            if (metric == "pd") {
                metricToggle.isChecked = true
                weightValue.hint = c.getString(c.getColumnIndex("weightPD"))
                heightValue.hint = c.getString(c.getColumnIndex("heightFT"))
            } else {
                metricToggle.isChecked = false
                weightValue.hint = c.getString(c.getColumnIndex("weightKG"))
                heightValue.hint = c.getString(c.getColumnIndex("heightSM"))
            }

        }

        genderToggle.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                gender = "female"
            } else {
                gender = "male"
            }
        }

        metricToggle.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                metric = "pd"
            } else {
                metric = "kg"
            }
        }



        userApplyBtn.setOnClickListener {
            if (weightValue.text.isNotEmpty() && heightValue.text.isNotEmpty()) {
                weight = weightValue.text.toString().toDouble()
                height = heightValue.text.toString().toDouble()

                if (metric == "pd") {
                    cv.put("weightPD", weight)
                    cv.put("heightFT", height)
                } else {
                    cv.put("weightKG", weight)
                    cv.put("heightSM", height)
                }

                cv.put("weightSystem", metric)
                cv.put("gender", gender)
                if (c.moveToFirst()) {
                    db1.update("userDB", cv, null, null)
                } else {
                    db1.insert("userDB", null, cv)
                }

            } else {
                cv.put("weightSystem", metric)
                cv.put("gender", gender)
                if (c.moveToFirst()) {
                    db1.update("userDB", cv, null, null)
                } else {
                    db1.insert("userDB", null, cv)
                }
            }
            if (gender == "male") {
                CommonInfo.instance.full_body = R.drawable.full_body
                CommonInfo.instance.situps = R.drawable.situps
                CommonInfo.instance.pushups = R.drawable.pushups
                CommonInfo.instance.squats = R.drawable.squats
                CommonInfo.instance.pullups = R.drawable.pullups
            } else if (gender == "female") {
                CommonInfo.instance.full_body = R.drawable.full_body_fem
                CommonInfo.instance.situps = R.drawable.sit_ups_fem
                CommonInfo.instance.pushups = R.drawable.push_ups_fem
                CommonInfo.instance.squats = R.drawable.squats_fem
                CommonInfo.instance.pullups = R.drawable.pull_ups_fem
            }
            val intent = Intent(this.applicationContext, MainActivity::class.java)
            startActivity(intent)
        }

    }
}
