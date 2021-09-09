package by.buglak.fitflow.ui.training

import android.content.ContentValues
import android.content.Context
import by.buglak.fitflow.LoginScreenActivity
import by.buglak.fitflow.MainActivity
import by.buglak.fitflow.R
import by.buglak.fitflow.ui.training.common.DBHelper

class TrainingsCollection {
    var trainingCollection = ArrayList<Trainings>()
    var trainingText = arrayListOf<Int>(
        R.string.full_body,
        R.string.sit_ups,
        R.string.push_ups,
        R.string.squats,
        R.string.pull_ups
    )

    var trainingImg = arrayListOf<Int>(
        CommonInfo.instance.full_body,
        CommonInfo.instance.situps,
        CommonInfo.instance.pushups,
        CommonInfo.instance.squats,
        CommonInfo.instance.pullups
    )


    var trainingTag = arrayListOf<String>("fullbody", "situps", "pushups", "squats", "pullups")

    fun createTrainingCollection() {

        for (i in 0 until trainingText.size) {
            trainingCollection.add(
                Trainings(trainingImg[i], trainingText[i], trainingTag[i], 15)
            )
        }
    }
}