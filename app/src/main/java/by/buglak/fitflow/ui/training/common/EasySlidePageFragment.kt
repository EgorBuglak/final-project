package by.buglak.fitflow.ui.training.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.buglak.fitflow.R
import kotlinx.android.synthetic.main.training_slide_fragment_easy.*

class EasySlidePageFragment(): Fragment() {
    var type = TrainingType.instance.type
    val training = TrainingCollection(TrainingType.instance.type)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        training.buildTrainingDays("easy")
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.training_slide_fragment_easy, container, false)

        return root
    }

    override fun onStart() {
        super.onStart()

        trainingRecyclerViewEasy.adapter =
            this.context?.let { TrainingAdapter(training.trainingCollection, it) }
        trainingRecyclerViewEasy.layoutManager = LinearLayoutManager(this.context)
        trainingRecyclerViewEasy.setHasFixedSize(true)
    }

}