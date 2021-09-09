package by.buglak.fitflow.ui.training.day

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import by.buglak.fitflow.MainActivity
import by.buglak.fitflow.R
import by.buglak.fitflow.ui.training.CommonInfo
import by.buglak.fitflow.ui.training.common.DBHelper
import by.buglak.fitflow.ui.training.common.TrainingInfoActivity
import kotlinx.android.synthetic.main.activity_rest_day.*
import java.text.SimpleDateFormat
import java.util.*

class RestDayActivity : AppCompatActivity() {

    var cv = ContentValues()
    var date = 0
    var finished = 0
    var id = CommonInfo.instance.id

    fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
    }

    fun getCurrentDateTime(): Date {
        return Calendar.getInstance().time
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rest_day)

        restDoneBtn.setOnClickListener(){
            val dbHelper = DBHelper(this.applicationContext)
            val db1 = dbHelper.writableDatabase
            val date = getCurrentDateTime().toString("yyyyMMdd")
            finished = 1
            cv.put("finished", finished)
            cv.put("date", date)
            db1.update("stateDB", cv, "exact = ?", arrayOf(id))
            val intent = Intent(this, TrainingInfoActivity::class.java)
            startActivity(intent)
        }
    }
}
