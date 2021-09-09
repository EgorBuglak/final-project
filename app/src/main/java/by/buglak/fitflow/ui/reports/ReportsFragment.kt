package by.buglak.fitflow.ui.reports

import android.content.ContentValues
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import by.buglak.fitflow.R
import by.buglak.fitflow.ui.training.CommonInfo
import by.buglak.fitflow.ui.training.common.DBHelper
import kotlinx.android.synthetic.main.fragment_reports.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class ReportsFragment : Fragment() {

    private lateinit var dashboardViewModel: ReportsViewModel

    var year = 0
    var month = 0
    var day = 0
    var date = ""
    var cv = ContentValues()
    var selection: String? = ""
    var dateIndex = 0

        override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProviders.of(this).get(ReportsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_reports, container, false)
        return root

    }

    override fun onStart() {
        super.onStart()
        var dbHelper = DBHelper(this.context!!)
        var db1 = dbHelper.writableDatabase
        selection = "finished == 1"
        var c = db1.query("stateDB", null, selection, null, null, null, null)
        if (c.moveToFirst()) {
            do {
                dateIndex = c.getColumnIndex("date")
                date = c.getInt(dateIndex).toString()
                year = date[0].toString().plus(date[1]).plus(date[2]).plus(date[3]).toInt()
                month = date[4].toString().plus(date[5]).toInt()
                day = date[6].toString().plus(date[7]).toInt()
                calendarView.markDate(year, month, day)
            } while (c.moveToNext())
        }
        var millis = CommonInfo.instance.timeTemp
        var hours = TimeUnit.MILLISECONDS.toHours(millis)
        millis -= TimeUnit.HOURS.toMillis(hours)
        var minutes = TimeUnit.MILLISECONDS.toMinutes(millis)
        millis -= TimeUnit.MINUTES.toMillis(minutes)
        var seconds = TimeUnit.MILLISECONDS.toSeconds(millis)

calendarText.text = "${hours}hr ${minutes}min ${seconds}sec"
    }
}
