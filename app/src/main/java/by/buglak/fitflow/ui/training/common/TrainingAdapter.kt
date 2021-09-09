package by.buglak.fitflow.ui.training.common

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.buglak.fitflow.R
import by.buglak.fitflow.ui.training.CommonInfo
import by.buglak.fitflow.ui.training.day.RestDayActivity
import by.buglak.fitflow.ui.training.day.TrainingDayActivity
import kotlinx.android.synthetic.main.recycler_item.view.*


class TrainingAdapter(val list: ArrayList<Training>, var context: Context) :
    RecyclerView.Adapter<TrainingAdapter.TrainingViewHolder>() {
    class TrainingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainingViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
        return TrainingViewHolder(itemView)

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: TrainingViewHolder, position: Int) {
        val myView = holder.itemView
        myView.dayNumber.text = "Day ${list[position].day}"
//        if (CommonInfo.instance.type == "Full body") {
//            myView.exerciseCount.text = ""
//        } else {
//            myView.exerciseCount.text = "${list[position].total} ${list[position].type}"
//        }
        myView.exerciseCount.text = "${list[position].total} ${list[position].type}"
        if (!list[position].trainingDay && list[position].finished != 1) {
            myView.trainingStateImg.setImageResource(R.drawable.rest_cup)
        } else if (list[position].trainingDay && list[position].finished != 1) {
            myView.trainingStateImg.setImageDrawable(null)
        } else if(list[position].finished == 1) {
            myView.trainingStateImg.setImageResource(R.drawable.done_img)
        }
        myView.dayItem.setOnClickListener {
            CommonInfo.instance.dayNumber = myView.dayNumber.text as String
            CommonInfo.instance.rep = list[position].repeats

            CommonInfo.instance.id = list[position].id
            if (list[position].trainingDay) {
                CommonInfo.instance.totalRep = list[position].total.toInt()
                val intent = Intent(context, TrainingDayActivity()::class.java)
                context.startActivity(intent)
            } else {
                val intent = Intent(context, RestDayActivity()::class.java)
                context.startActivity(intent)
            }

        }
    }
}
