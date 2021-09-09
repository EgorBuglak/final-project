package by.buglak.fitflow.ui.training.day

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import by.buglak.fitflow.R
import by.buglak.fitflow.ui.training.CommonInfo
import by.buglak.fitflow.ui.training.common.DBHelper
import by.buglak.fitflow.ui.training.common.TrainingInfoActivity
import by.buglak.fitflow.ui.training.trainingDay.ExactTrainingActivity
import kotlinx.android.synthetic.main.activity_training_day.*
import kotlinx.android.synthetic.main.recycler_item.view.*

class TrainingDayActivity() : AppCompatActivity() {
    val day = DayCollection()
    lateinit var id: String
    lateinit var mode1: String
    var cv = ContentValues()
    var selection: String? = ""
    var selectionArgs: Array<String>? = null
    var finished = 0
    var finishIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_training_day)
        var dbHelper: DBHelper = DBHelper(this.applicationContext)
        id = CommonInfo.instance.id
        var db1 = dbHelper.writableDatabase
        selection = "exact == ?"
        selectionArgs = arrayOf(id)
        var c = db1.query("stateDB", null, selection, selectionArgs, null, null, null)
        if (c.moveToFirst()) {
            finishIndex = c.getColumnIndex("finished")
            finished = c.getInt(finishIndex)
            if (finished == 1) {
                trainingDayStartBtn.text = "Do it again"
                CommonInfo.instance.finished = 1
            } else {
                trainingDayStartBtn.text = "Start"
                CommonInfo.instance.finished = 0
            }
        } else {
            trainingDayStartBtn.text = "Start"
            CommonInfo.instance.finished = 0
        }
        c.close()
        db1.close()
        trainingDayGoBackBtn.setOnClickListener {
            val intent = Intent(this, TrainingInfoActivity::class.java)
            startActivity(intent)
        }
        var num = 0
        dayTrainingTitle.text = CommonInfo.instance.dayNumber
        dayImageView.setImageResource(CommonInfo.instance.img)
        trainingDayTextMode.text = CommonInfo.instance.trainingMode
        setTrainingMode()
        day.getDay()
        trainingDayTotal.text = "${CommonInfo.instance.type} (${CommonInfo.instance.totalRep})"
        trainingDayStartBtn.setOnClickListener{
            val intent = Intent(this, ExactTrainingActivity::class.java)
            startActivity(intent)
        }
    }

    fun setTrainingMode() {
        when (CommonInfo.instance.trainingMode) {
            "Beginner" -> {
                dayEasyPoint.setImageResource(R.drawable.training_mode_point_on)
                dayNormalPoint.setImageResource(R.drawable.training_mode_point)
                dayHardPoint.setImageResource(R.drawable.training_mode_point)
            }
            "Normal" -> {
                dayEasyPoint.setImageResource(R.drawable.training_mode_point_on)
                dayNormalPoint.setImageResource(R.drawable.training_mode_point_on)
                dayHardPoint.setImageResource(R.drawable.training_mode_point)
            }
            "Hard" -> {
                dayEasyPoint.setImageResource(R.drawable.training_mode_point_on)
                dayNormalPoint.setImageResource(R.drawable.training_mode_point_on)
                dayHardPoint.setImageResource(R.drawable.training_mode_point_on)
            }
        }
    }

    fun getNum() {

    }

    override fun onStart() {
        super.onStart()

        dayRecyclerView.adapter = DayAdapter(day.dayCollection)
        dayRecyclerView.layoutManager = LinearLayoutManager(this)
        dayRecyclerView.setHasFixedSize(true)
    }
}
