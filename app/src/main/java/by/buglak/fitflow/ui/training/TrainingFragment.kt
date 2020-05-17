package by.buglak.fitflow.ui.training

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import by.buglak.fitflow.R
import by.buglak.fitflow.ui.training.situps.SitupsInfoActivity
import kotlinx.android.synthetic.main.fragment_training.*

class TrainingFragment : Fragment() {

    private lateinit var trainingViewModel: TrainingViewModel
    val training = TrainingCollection()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        training.createTrainingCollection()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        trainingViewModel =
            ViewModelProviders.of(this).get(TrainingViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_training, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        trainingViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root


    }


    override fun onStart() {
        super.onStart()
        trainingRecyclerView.adapter =
            this.context?.let { TrainingAdapter(training.trainingCollection, it) }
        trainingRecyclerView.layoutManager = LinearLayoutManager(this.context)
        trainingRecyclerView.setHasFixedSize(true)
//        situpsBtn.setOnClickListener {
//            val intent = Intent(this.context, SitupsInfoActivity::class.java)
//            startActivity(intent)
//        }
    }



}
