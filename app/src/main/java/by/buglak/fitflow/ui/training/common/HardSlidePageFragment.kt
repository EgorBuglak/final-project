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
import kotlinx.android.synthetic.main.training_slide_fragment_hard.*

class HardSlidePageFragment(): Fragment() {
    val training = TrainingCollection(TrainingType.instance.type)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        training.buildTrainingDays("hard")
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.training_slide_fragment_hard, container, false)

        return root
    }

    override fun onStart() {
        super.onStart()

        trainingRecyclerViewHard.adapter =
            this.context?.let { TrainingAdapter(training.trainingCollection, it) }
        trainingRecyclerViewHard.layoutManager = LinearLayoutManager(this.context)
        trainingRecyclerViewHard.setHasFixedSize(true)
    }
}