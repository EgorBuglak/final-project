package by.buglak.fitflow.ui.training

import android.content.Context
import androidx.fragment.app.Fragment
import by.buglak.fitflow.R
import java.sql.Time

class CommonInfo {
    var test = 1
    var totalRep = 0
    var repeats = ArrayList<ArrayList<Int>>()
    var trainingMode = ""
    var type = ""
    var img = R.drawable.go_back_img
    var titleText = ""
    var rep = ArrayList<Int>()
    var itemId = R.id.navigation_training
    var time = 0
    var animation = 0
    var dayNumber = ""
    var id = ""
    var finished = 0
    lateinit var context: Context
    var visible = false
    var kCal = 0
    var timeTemp:Long = 0
    var full_body = R.drawable.full_body
    var situps = R.drawable.situps
    var pushups = R.drawable.pushups
    var squats = R.drawable.squats
    var pullups = R.drawable.pullups

    companion object {
        val instance = CommonInfo()
    }
}