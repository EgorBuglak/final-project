package by.buglak.fitflow.ui.training.common

import android.content.ContentValues
import by.buglak.fitflow.ui.training.CommonInfo

class TrainingCollection(var type: String) {
    var trainingCollection = ArrayList<Training>()
    lateinit var repeats: ArrayList<Int>
    var dayCount = 1
    var total = 0
    var repeatsEasy = arrayListOf(1)
    var repeatsNormal = arrayListOf(1)
    var repeatsHard = arrayListOf(1)
    var switch = 1
    var countTillRest = 1
    var trainingType = ""
    lateinit var id: String
    lateinit var mode1: String
    var cv = ContentValues()
    var selection: String? = ""
    var selectionArgs: Array<String>? = null
    var finished = 0
    var finishIndex = 0


    fun check(mode: String) {
        mode1 = mode
        when (mode) {
            "easy" -> {
                repeats = repeatsEasy
            }
            "normal" -> {
                repeats = repeatsNormal
            }
            "hard" -> {
                repeats = repeatsHard
            }
        }
    }

    fun getType(mode: String) {
        when (type) {
            "situps" -> {
                repeatsEasy = RepeatsValue().easySitups
                repeatsNormal = RepeatsValue().normalSitups
                repeatsHard = RepeatsValue().hardSitups
                trainingType = "sit-ups"
            }
            "pullups" -> {
                repeatsEasy = RepeatsValue().easyPullUps
                repeatsNormal = RepeatsValue().normalPullUps
                repeatsHard = RepeatsValue().hardPullUps
                trainingType = "pull-ups"
            }
            "pushups" -> {
                repeatsEasy = RepeatsValue().easyPushUps
                repeatsNormal = RepeatsValue().normalPushUps
                repeatsHard = RepeatsValue().hardPushUps
                trainingType = "push-ups"
            }
            "squats" -> {
                repeatsEasy = RepeatsValue().easySquats
                repeatsNormal = RepeatsValue().normalSquats
                repeatsHard = RepeatsValue().hardSquats
                trainingType = "squats"
            }
            "fullbody" -> {
                repeatsEasy = RepeatsValue().easyFullBody
                repeatsNormal = RepeatsValue().normalFullBody
                repeatsHard = RepeatsValue().hardFullBody
                trainingType = "fullbody"
            }
        }
        check(mode)
    }


    fun incDays() {
        dayCount += 1
    }


    fun trainingDay() {
        total = repeats.sum()
        CommonInfo.instance.repeats.add(repeats)
        val rep = arrayListOf<Int>()
        rep.clear()
        repeats.forEach {
            rep.add(it)
        }
        var dbHelper = DBHelper(CommonInfo.instance.context)
        id = type + mode1 + dayCount
        var db1 = dbHelper.writableDatabase
        selection = "exact == ?"
        selectionArgs = arrayOf(id)
        var c = db1.query("stateDB", null, selection, selectionArgs, null, null, null)
        if (c.moveToFirst()) {
            finishIndex = c.getColumnIndex("finished")
            finished = c.getInt(finishIndex)
        } else {
            cv.put("exact", id)
            cv.put("finished", finished)
            db1.insert("stateDB", "date", cv)

        }
        c.close()
        trainingCollection.add(
            Training(
                dayCount, rep, total.toString(), trainingType, true, id, finished
            )
        )

        finished = 0
db1.close()
    }

    fun restDay() {
        CommonInfo.instance.repeats.add(repeats)
        var dbHelper = DBHelper(CommonInfo.instance.context)
        id = type + mode1 + dayCount
        var db1 = dbHelper.writableDatabase
        selection = "exact == ?"
        selectionArgs = arrayOf(id)
        var c = db1.query("stateDB", null, selection, selectionArgs, null, null, null)
        if (c.moveToFirst()) {
            finishIndex = c.getColumnIndex("finished")
            finished = c.getInt(finishIndex)
        } else {
            cv.put("exact", id)
            cv.put("finished", finished)
            db1.insert("stateDB", "date", cv)

        }
        c.close()
        trainingCollection.add(
            Training(
                dayCount, repeats, "", "Rest", false, id, finished
            )
        )
        finished = 0
        db1.close()
    }

    fun buildTrainingDays(mode: String) {

        getType(mode)
        trainingDay()
        while (dayCount < 30) {
            if (countTillRest < 3) {
                when (switch) {
                    1 -> {
                        //incOut
                        repeats[0] = repeats[0] + 1
                        repeats[repeats.size - 1] = repeats[repeats.size - 1] + 1
                        switch += 1
                        countTillRest += 1
                        incDays()
                        trainingDay()
                    }
                    2 -> {
                        //incIns
                        for (i in 1..repeats.size - 2) {
                            repeats[i] = repeats[i] + 1
                        }
                        switch -= 1
                        countTillRest += 1
                        incDays()
                        trainingDay()
                    }
                }
            } else {
                //rest
                countTillRest -= 3
                incDays()
                restDay()
            }
        }
    }

//    class DBHelper(context: Context?) :
//        SQLiteOpenHelper(context, "stateDB", null, 1) {
//        override fun onCreate(db1: SQLiteDatabase) {
//            db1.execSQL(
//                "create table table1 ("
//                        + "id text,"
//                        + "finished integer,"
//                        + "date integer" + ");"
//            )
//        }
//
//        override fun onUpgrade(
//            db1: SQLiteDatabase,
//            oldVersion: Int,
//            newVersion: Int
//        ) {
//        }
//    }

}