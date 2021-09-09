package by.buglak.fitflow.ui.training.common

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.buglak.fitflow.R
import by.buglak.fitflow.ui.training.day.TrainingDayActivity
import kotlinx.android.synthetic.main.training_slide_fragment_normal.*

class NormalSlidePageFragment() : Fragment() {
    val training = TrainingCollection(TrainingType.instance.type)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        training.buildTrainingDays("normal")
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.training_slide_fragment_normal, container, false)

        return root
    }

    override fun onStart() {
        super.onStart()

        trainingRecyclerViewNormal.adapter =
            this.context?.let { TrainingAdapter(training.trainingCollection, it) }
        trainingRecyclerViewNormal.layoutManager = LinearLayoutManager(this.context)
        trainingRecyclerViewNormal.setHasFixedSize(true)
    }
}