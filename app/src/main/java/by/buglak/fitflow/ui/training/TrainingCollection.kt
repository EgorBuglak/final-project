package by.buglak.fitflow.ui.training

import android.graphics.drawable.Drawable
import androidx.core.graphics.drawable.toDrawable
import by.buglak.fitflow.R

class TrainingCollection {
    var trainingCollection = ArrayList<Training>()
    var trainingText = arrayListOf<Int>(
        R.string.full_body,
        R.string.sit_ups,
        R.string.push_ups,
        R.string.squats,
        R.string.pull_ups
    )

    var trainingImg = arrayListOf<Int>(R.drawable.full_body,
        R.drawable.situps,
        R.drawable.pushups,
        R.drawable.squats,
        R.drawable.pullups)


    var trainingTag = arrayListOf<String>("fullBody", "situps", "pushups", "squats", "pullups")

    fun createTrainingCollection(){
        for (i in 0 until trainingText.size) {
            trainingCollection.add(
                Training(trainingImg[i], trainingText[i], trainingTag[i])
            )
        }
    }
}