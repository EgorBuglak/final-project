package by.buglak.fitflow.ui.me

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import by.buglak.fitflow.R

const val REMIND ="REMIND"

class ReminderReceiver :BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val intent = Intent(context, ReminderActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(context, 999, intent, 0)

        val notificationBuilder = context?.let {
NotificationCompat.Builder(it, REMIND)
    .setSmallIcon(R.drawable.ic_launcher_background)
    .setContentTitle("Reminding")
    .setContentText("Time to work out")
    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
    .setContentIntent(pendingIntent)
    .setDeleteIntent(pendingIntent)
        }
        val notificationManager = context?.let { NotificationManagerCompat.from(it) }
        notificationBuilder?.build()?.let { notificationManager?.notify(999, it) }
    }
}