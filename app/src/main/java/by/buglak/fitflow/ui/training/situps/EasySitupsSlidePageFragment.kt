package by.buglak.fitflow.ui.training.situps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.buglak.fitflow.R
import kotlinx.android.synthetic.main.activity_situps_info.*
import kotlinx.android.synthetic.main.situps_slide_fragment_easy.*

class EasySitupsSlidePageFragment : Fragment() {

    val situp = SitupCollection()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        situp.buildSitupsDays("easy")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.situps_slide_fragment_easy, container, false)

        return root
    }


    override fun onStart() {
        super.onStart()

        situpsResyclerView.adapter = SitupsAdapter(situp.situpCollection)
        situpsResyclerView.layoutManager = LinearLayoutManager(this.context)
        situpsResyclerView.setHasFixedSize(true)
    }
}