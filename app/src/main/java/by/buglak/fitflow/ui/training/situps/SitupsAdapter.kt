package by.buglak.fitflow.ui.training.situps

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.buglak.fitflow.R
import kotlinx.android.synthetic.main.resycler_item.view.*

class SitupsAdapter(val list: ArrayList<Situp>) :
    RecyclerView.Adapter<SitupsAdapter.SitupsViewHolder>() {
    class SitupsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SitupsViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.resycler_item, parent, false)
        return SitupsViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: SitupsViewHolder, position: Int) {
        val myView = holder.itemView
        myView.dayNumber.text = list.get(position).day
        myView.exerciseCount.text = list.get(position).total
        if (!list.get(position).trainingDay) {
            myView.trainingStateImg.setImageResource(R.drawable.rest_cup)
        } else if (list.get(position).trainingDay){
            myView.trainingStateImg.setImageDrawable(null)
        }
    }
}