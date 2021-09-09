package by.buglak.fitflow.ui.training.day

import by.buglak.fitflow.R
import by.buglak.fitflow.ui.training.CommonInfo

class DayCollection() {

    var dayCollection = ArrayList<Day>()

    var repeats = CommonInfo.instance.rep

    var count = 1
    var anim = CommonInfo.instance.animation
    var type = CommonInfo.instance.type


    fun getDay() {

        repeats.forEach {
            if (CommonInfo.instance.type == "Full body") {
                when (count) {
                    1 -> {
                        anim = R.drawable.pushups_anim
                        count += 1
                        type = "Push-ups"
                    }
                    2 -> {
                        anim = R.drawable.situps_anim
                        count += 1
                        type = "Sit-ups"
                    }
                    3 -> {
                        anim = R.drawable.squats_anim
                        count += 1
                        type = "Squats"
                    }
                    4 -> {
                        anim = R.drawable.pullups_anim
                        count = 1
                        type = "Pull-ups"
                    }
                }

            }
            dayCollection.add(
                Day(
                    "x $it", anim, type
                )
            )
        }
    }
}