package by.buglak.fitflow.ui.training.trainingDay

import android.content.ContentValues
import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.os.CountDownTimer
import android.os.SystemClock
import android.view.View
import android.widget.Chronometer
import androidx.appcompat.app.AppCompatActivity
import by.buglak.fitflow.MainActivity
import by.buglak.fitflow.R
import by.buglak.fitflow.ui.training.CommonInfo
import by.buglak.fitflow.ui.training.common.DBHelper
import by.buglak.fitflow.ui.training.day.TrainingDayActivity
import kotlinx.android.synthetic.main.activity_exact_training.*
import java.text.SimpleDateFormat
import java.util.*


class ExactTrainingActivity : AppCompatActivity() {
    private lateinit var animate: AnimationDrawable
    var exerciseAnim = CommonInfo.instance.animation
    var index = 0
    var repeats = CommonInfo.instance.rep
    var time = CommonInfo.instance.time
    var time1 = time
    var repeat = repeats[index]
    var cv = ContentValues()
    var cv1 = ContentValues()
    var finished = 0
    var done = true
    var id = CommonInfo.instance.id
    var mChronometer: Chronometer? = null
    var type = CommonInfo.instance.type

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exact_training)

        exactGoBack.setOnClickListener {
            val intent = Intent(this, TrainingDayActivity::class.java)
            startActivity(intent)
        }

        exactType.text = CommonInfo.instance.type
        exactNumber.text = repeats[index].toString()


        bottomAnim.apply {
            setBackgroundResource(exerciseAnim)
            animate = background as AnimationDrawable
        }
        animate.start()
        topAnim.apply {
            setBackgroundResource(exerciseAnim)
            animate = background as AnimationDrawable
        }
        animate.start()
        exactToDo.text = "Do $repeat reps"
        mChronometer = findViewById(R.id.chronometer)
        mChronometer?.base = SystemClock.elapsedRealtime()
        mChronometer?.start()

        exactCircle.setOnClickListener {
            if (done) {
                doneClick()
                done = false
            } else {
                timer.cancel()
                timer.onFinish()
                done = true
            }
        }

        increase.setOnClickListener {
            if (repeat < repeats[index] * 2) {
                repeat += 1
                exactNumber.text = repeat.toString()
                exactToDo.text = "Do $repeat reps"
            } else {
                exactNumber.text = "$repeat"
                exactToDo.text = "Do $repeat reps"
            }
        }
        decrease.setOnClickListener {
            if (repeat > 1) {
                repeat -= 1
                exactNumber.text = "$repeat"
                exactToDo.text = "Do $repeat reps"
            } else {
                exactNumber.text = "$repeat"
                exactToDo.text = "Do $repeat reps"
            }
        }
        bottomLane.visibility = View.INVISIBLE
    }

    fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
    }

    fun getCurrentDateTime(): Date {
        return Calendar.getInstance().time
    }

    private fun doneClick() {
        if (index < repeats.size - 1) {
            bottomNext.text = "NEXT ${index + 2}/${repeats.size}"
            bottomRepeat.text = "x${repeats[index + 1]} ${CommonInfo.instance.type}"
            bottomLane.visibility = View.VISIBLE
            increase.visibility = View.INVISIBLE
            decrease.visibility = View.INVISIBLE
            exactDone.text = "Stop"
            exactToDo.visibility = View.INVISIBLE
            exactType.text = "Rest time"
            timer.start()
        } else {
            val dbHelper = DBHelper(this.applicationContext)
            val db1 = dbHelper.writableDatabase
//            finished = 1
//            val date = getCurrentDateTime().toString("yyyyMMdd")
//            cv.put("finished", finished)
//            cv.put("date", date)
//            db1.update("stateDB", cv, "exact = ?", arrayOf(id))
            if (CommonInfo.instance.finished != 1) {
                var cv2 = ContentValues()
                cv2.put("fullFinished", 0)
//                cv2.put("fullLast", "")
//                cv2.put("sitLast", "")
//                cv2.put("pushLast", "")
//                cv2.put("sqtLast", "")
//                cv2.put("pullLast", "")
                db1.insert("progressDB", null, cv2)
                when (type) {
                    "Full body" -> {
                        var col = arrayOf("fullFinished")
                        var c = db1.query("progressDB", col, null, null, null, null, null)
                        cv1.put("fullLast", "${CommonInfo.instance.trainingMode} " + CommonInfo.instance.dayNumber)
                        if (c.moveToFirst() && (c.getString(c.getColumnIndex("fullFinished"))) != null) {
                            var fin = c.getString(c.getColumnIndex("fullFinished")).toInt() + 1
                            cv1.put("fullFinished", fin)
                            db1.update("progressDB", cv1, null, null)
                        } else {
                            cv1.put("fullFinished", 1)
//                            db1.insert("progressDB", null, cv1)
                            db1.update("progressDB", cv1, null, null)
                        }
                        c.close()
                    }
                    "Sit-ups" -> {
                        var col = arrayOf("sitFinished")
                        var c = db1.query("progressDB", col, null, null, null, null, null)
                        cv1.put("sitLast", "${CommonInfo.instance.trainingMode} " + CommonInfo.instance.dayNumber)
                        CommonInfo.instance.test = 2
                        if (c.moveToFirst()
                            && (c.getString(c.getColumnIndex("sitFinished"))) != null
                        ) {
                            var fin = c.getString(c.getColumnIndex("sitFinished")).toInt() + 1
                            cv1.put("sitFinished", fin)
                            db1.update("progressDB", cv1, null, null)
                        } else {
                            cv1.put("sitFinished", 1)
//                            db1.insert("progressDB", null, cv1)
                            db1.update("progressDB", cv1, null, null)
                        }
                        c.close()
                    }
                    "Push-ups" -> {
                        var col = arrayOf("pushFinished")
                        var c = db1.query("progressDB", col, null, null, null, null, null)
                        cv1.put("pushLast", "${CommonInfo.instance.trainingMode} " + CommonInfo.instance.dayNumber)
                        if (c.moveToFirst() && (c.getString(c.getColumnIndex("pushFinished"))) != null) {
                            var fin = c.getString(c.getColumnIndex("pushFinished")).toInt() + 1
                            cv1.put("pushFinished", fin)
                            db1.update("progressDB", cv1, null, null)
                        } else {
                            cv1.put("pushFinished", 1)
                            db1.update("progressDB", cv1, null, null)
                        }
                        c.close()
                    }
                    "Squats" -> {
                        var col = arrayOf("sqtFinished")
                        var c = db1.query("progressDB", col, null, null, null, null, null)
                        cv1.put("sqtLast", "${CommonInfo.instance.trainingMode} " + CommonInfo.instance.dayNumber)
                        if (c.moveToFirst() && (c.getString(c.getColumnIndex("sqtFinished"))) != null) {
                            var fin = c.getInt(c.getColumnIndex("sqtFinished"))
                            cv1.put("sqtFinished", fin + 1)
                            db1.update("progressDB", cv1, null, null)
                        } else {
                            cv1.put("sqtFinished", 1)
                            db1.update("progressDB", cv1, null, null)
                        }
                        c.close()
                    }
                    "Pull-ups" -> {
                        var col = arrayOf("pullFinished")
                        var c = db1.query("progressDB", col, null, null, null, null, null)
                        cv1.put("pullLast", "${CommonInfo.instance.trainingMode} " + CommonInfo.instance.dayNumber)
                        if (c.moveToFirst() && (c.getString(c.getColumnIndex("pullFinished"))) != null) {
                            var fin = c.getInt(c.getColumnIndex("pullFinished"))
                            cv1.put("pullFinished", fin + 1)
                            db1.update("progressDB", cv1, null, null)
                        } else {
                            cv1.put("pullFinished", 1)
                            db1.update("progressDB", cv1, null, null)
                        }
                        c.close()
                    }
                }
            }
            finished = 1
            val date = getCurrentDateTime().toString("yyyyMMdd")
            cv.put("finished", finished)
            cv.put("date", date)
            db1.update("stateDB", cv, "exact = ?", arrayOf(id))
            CommonInfo.instance.itemId = R.id.navigation_reports
            CommonInfo.instance.visible = true
            CommonInfo.instance.timeTemp = SystemClock.elapsedRealtime() - mChronometer!!.base
            index = 0
            mChronometer?.stop()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }
    }

    val timer = object : CountDownTimer((time * 1000).toLong(), 1000) {
        override fun onTick(millisUntilFinished: Long) {

            exactNumber.text = "$time1"
            time1--
        }

        override fun onFinish() {
            time1 = time
            index += 1
            bottomLane.visibility = View.INVISIBLE
            exactNumber.text = repeats[index].toString()
            exactType.text = CommonInfo.instance.type
            increase.visibility = View.VISIBLE
            decrease.visibility = View.VISIBLE
            exactDone.text = "Done"
            exactToDo.visibility = View.VISIBLE
            repeat = repeats[index]
            exactToDo.text = "Do $repeat reps"
        }
    }

}
