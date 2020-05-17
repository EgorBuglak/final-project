package by.buglak.fitflow.ui.training.situps

class SitupCollection : SitupsInfoActivity() {
    var situpCollection = ArrayList<Situp>()


    var x1 = 2
    var x2 = 3
    var x3 = 2
    var x4 = 2
    var x5 = 3
    var x6 = 3
    var x7 = 3
    var x8 = 3
    var x9 = 2
    var x10 = 3
    var dayCount = 1
    var repeatsEasy = arrayListOf(x1, x2, x3, x4, x5, x6)
    var repeatsNormal = arrayListOf(x1, x2, x3, x4, x5, x6, x7, x8)
    var repeatsHard = arrayListOf(x1, x2, x3, x4, x5, x6, x7, x8, x9, x10)
    lateinit var repeats: ArrayList<Int>
    var total = 0

    fun check(mode: String) {
        when (mode) {
            "easy" -> {
                repeatsEasy = arrayListOf(x1, x2, x3, x4, x5, x6)
                repeats = repeatsEasy
            }
            "normal" -> {
                repeatsNormal = arrayListOf(x1, x2, x3, x4, x5, x6, x7, x8)
                repeats = repeatsNormal
            }
            "hard" -> {
                repeatsHard = arrayListOf(x1, x2, x3, x4, x5, x6, x7, x8, x9, x10)
                repeats = repeatsHard
            }
        }
    }


    fun incOutside(mode: String) {
        when (mode) {
            "easy" -> {
                x1 += 1
                x6 += 1
            }
            "normal" -> {
                x1 += 1
                x8 += 1
            }
            "hard" -> {
                x1 += 1
                x10 += 1
            }
        }
        incDays()
        situpsDay(mode)
    }

    fun incInside(mode: String) {
        when (mode) {
            "easy" -> {
                x2 += 1; x3 += 1; x4 += 1; x5 += 1
            }
            "normal" -> {
                x2 += 1; x3 += 1; x4 += 1; x5 += 1; x6 += 1; x7 += 1
            }
            "hard" -> {
                x2 += 1; x3 += 1; x4 += 1; x5 += 1; x6 += 1; x7 += 1; x8 += 1; x9 += 1
            }
        }
        incDays()
        situpsDay(mode)
    }

    fun incDays() {
        dayCount += 1
    }


    fun situpsDay(mode: String) {
        check(mode)
        total = repeats.sum()
        situpCollection.add(
            Situp(
                "Day $dayCount", repeats, "$total sit-ups", true
            )
        )
    }

    fun restDay(mode: String) {
        check(mode)
        incDays()
        situpCollection.add(
            Situp(
                "Day $dayCount", repeats, "Rest", false
            )
        )
    }

    fun buildSitupsDays(mode: String) {
        situpsDay(mode)
        incOutside(mode)
        incInside(mode)
        restDay(mode)
        for (i in 1..3) {
            incOutside(mode)
            incInside(mode)
            incOutside(mode)
            restDay(mode)
            incInside(mode)
            incOutside(mode)
            incInside(mode)
            restDay(mode)
        }
        incOutside(mode)
        incInside(mode)
        incOutside(mode)
    }
}