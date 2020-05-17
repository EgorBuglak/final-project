package by.buglak.fitflow.ui.training

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.buglak.fitflow.R
import by.buglak.fitflow.ui.training.fullBody.FullBodyInfoActivity
import by.buglak.fitflow.ui.training.pullUps.PullUpsInfoActivity
import by.buglak.fitflow.ui.training.pushUps.PushUpsInfoActivity
import by.buglak.fitflow.ui.training.situps.SitupsInfoActivity
import by.buglak.fitflow.ui.training.squats.SquatsInfoActivity
import kotlinx.android.synthetic.main.resycler_main_training_item.view.*


class TrainingAdapter(val list: ArrayList<Training>, var context: Context) :
    RecyclerView.Adapter<TrainingAdapter.TrainingViewHolder>() {
    class TrainingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainingViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.resycler_main_training_item, parent, false)
        return TrainingViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }


    override fun onBindViewHolder(holder: TrainingViewHolder, position: Int) {
        val myView = holder.itemView
        myView.trainingBtn.setImageResource(list.get(position).imgSrc)
        myView.trainingBtnText.setText(list.get(position).text)
        myView.trainingBtn.setOnClickListener {
            when (list.get(position).tag) {
                "fullBody" -> {
                    val intent = Intent(context, FullBodyInfoActivity::class.java)
                    context.startActivity(intent)
                }
                "situps" -> {
                    val intent = Intent(context, SitupsInfoActivity::class.java)
                    context.startActivity(intent)
                }
                "pushups" -> {
                    val intent = Intent(context, PushUpsInfoActivity::class.java)
                    context.startActivity(intent)
                }
                "squats" -> {
                    val intent = Intent(context, SquatsInfoActivity::class.java)
                    context.startActivity(intent)
                }
                "pullups" -> {
                    val intent = Intent(context, PullUpsInfoActivity::class.java)
                    context.startActivity(intent)
                }
            }
        }
    }

}