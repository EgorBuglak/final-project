package by.buglak.fitflow.ui.training

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.buglak.fitflow.R
import by.buglak.fitflow.ui.training.common.DBHelper
import by.buglak.fitflow.ui.training.common.TrainingInfoActivity
import by.buglak.fitflow.ui.training.common.TrainingType
import kotlinx.android.synthetic.main.resycler_main_training_item.view.*


class TrainingsAdapter(val list: ArrayList<Trainings>, var context: Context) :
    RecyclerView.Adapter<TrainingsAdapter.TrainingViewHolder>() {
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
        val dbHelper = DBHelper(this.context)
        val db1 = dbHelper.writableDatabase
        var c = db1.query("progressDB", null, null, null, null, null, null)
            var finished = arrayListOf<Int>(0,30,46,5,0)
        var last = arrayListOf<String>("", "", "", "", "")
        if(c.moveToFirst()){

            finished = arrayListOf<Int>(
                Math.round(((c.getDouble(c.getColumnIndex("fullFinished"))) / 90) * 100).toInt(),
                Math.round(((c.getDouble(c.getColumnIndex("sitFinished"))) / 90) * 100).toInt(),
                Math.round(((c.getDouble(c.getColumnIndex("pushFinished"))) / 90) * 100).toInt(),
                Math.round(((c.getDouble(c.getColumnIndex("sqtFinished"))) / 90) * 100).toInt(),
                Math.round(((c.getDouble(c.getColumnIndex("pullFinished"))) / 90) * 100).toInt()
            )
//            last = finished[position].toString()
//            last = Math.round(((c.getDouble(c.getColumnIndex("fullFinished"))) / 90) * 100).toString()
            last = arrayListOf<String>(
                c.getString(c.getColumnIndex("fullLast")) ?: "",
                c.getString(c.getColumnIndex("sitLast")) ?: "",
                c.getString(c.getColumnIndex("pushLast")) ?: "",
                c.getString(c.getColumnIndex("sqtLast")) ?: "",
                c.getString(c.getColumnIndex("pullLast")) ?: ""
            )
//            last = CommonInfo.instance.test.toString()

        }
        c.close()




        val myView = holder.itemView
        myView.trainingBtn.setImageResource(list[position].imgSrc)
        myView.trainingBtnText.setText(list[position].text)
        myView.mainProgressBar.progress = finished[position]
        myView.trainingBtnLastTrain.text = last[position]
        myView.trainingBtnPers.text = finished[position].toString() + "%"
        myView.trainingBtn.setOnClickListener {
            when (list[position].tag) {
                "fullbody" -> {
                    val intent = Intent(context, TrainingInfoActivity()::class.java)
                    context.startActivity(intent)
                    TrainingType.instance.type = "fullbody"
                    CommonInfo.instance.type = "Full body"
                    CommonInfo.instance.img = list[position].imgSrc
                    CommonInfo.instance.titleText = "Full body"
                    CommonInfo.instance.animation = R.drawable.situps_anim

                }
                "situps" -> {
                    val intent = Intent(context, TrainingInfoActivity()::class.java)
                    context.startActivity(intent)
                    TrainingType.instance.type = "situps"
                    CommonInfo.instance.type = "Sit-ups"
                    CommonInfo.instance.img = list[position].imgSrc
                    CommonInfo.instance.titleText = "Sit-ups"
                    CommonInfo.instance.animation = R.drawable.situps_anim
                }
                "pushups" -> {
                    val intent = Intent(context, TrainingInfoActivity()::class.java)
                    context.startActivity(intent)
                    TrainingType.instance.type = "pushups"
                    CommonInfo.instance.type = "Push-ups"
                    CommonInfo.instance.img = list[position].imgSrc
                    CommonInfo.instance.titleText = "Push-ups"
                    CommonInfo.instance.animation = R.drawable.pushups_anim
                }
                "squats" -> {
                    val intent = Intent(context, TrainingInfoActivity()::class.java)
                    context.startActivity(intent)
                    TrainingType.instance.type = "squats"
                    CommonInfo.instance.type = "Squats"
                    CommonInfo.instance.img = list[position].imgSrc
                    CommonInfo.instance.titleText = "Squats"
                    CommonInfo.instance.animation = R.drawable.squats_anim
                }
                "pullups" -> {
                    val intent = Intent(context, TrainingInfoActivity()::class.java)
                    context.startActivity(intent)
                    TrainingType.instance.type = "pullups"
                    CommonInfo.instance.type = "Pull-ups"
                    CommonInfo.instance.img = list[position].imgSrc
                    CommonInfo.instance.titleText = "Pull-ups"
                    CommonInfo.instance.animation = R.drawable.pullups_anim
                }
            }
        }
    }

}