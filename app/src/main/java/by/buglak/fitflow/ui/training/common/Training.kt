package by.buglak.fitflow.ui.training.common

data class Training(
    var day: Int,
    var repeats: ArrayList<Int>,
    var total: String,
    var type: String,
    var trainingDay: Boolean,
    var id: String,
    var finished: Int
) {
}