package by.buglak.fitflow.ui.training.day

import android.graphics.drawable.AnimationDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.buglak.fitflow.R
import by.buglak.fitflow.ui.training.CommonInfo
import kotlinx.android.synthetic.main.recycler_day_item.view.*

class DayAdapter(val list: ArrayList<Day>) :
    RecyclerView.Adapter<DayAdapter.DayViewHolder>() {
    class DayViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private lateinit var animate: AnimationDrawable

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_day_item, parent, false)
        return DayViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: DayViewHolder, position: Int) {
        val myView = holder.itemView
        myView.dayRepeatsCount.text = list[position].repeatsInSet
        myView.dayTrainingType.text = list[position].type
        myView.anim.apply {
            setBackgroundResource(list[position].animation)
            animate = background as AnimationDrawable
        }
        animate.start()
    }
}