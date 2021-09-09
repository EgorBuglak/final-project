package by.buglak.fitflow.ui.me

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import by.buglak.fitflow.MainActivity
import by.buglak.fitflow.R
import kotlinx.android.synthetic.main.fragment_me.*

class MeFragment : Fragment() {

    private lateinit var notificationsViewModel: MeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
                ViewModelProviders.of(this).get(MeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_me, container, false)
        val userInfo: TextView = root.findViewById(R.id.userInfoMe)
        val training: TextView = root.findViewById(R.id.training_me)
        val reminder: TextView = root.findViewById(R.id.reminder_me)
        val disclaimer: TextView = root.findViewById(R.id.disclaimer_me)
        val privacy: TextView = root.findViewById(R.id.privacy_me)
        notificationsViewModel.text_user.observe(viewLifecycleOwner, Observer {
            userInfo.text = it
        })
        notificationsViewModel.text_training.observe(viewLifecycleOwner, Observer {
            training.text = it
        })
        notificationsViewModel.text_reminder.observe(viewLifecycleOwner, Observer {
            reminder.text = it
        })
        notificationsViewModel.text_disclaimer.observe(viewLifecycleOwner, Observer {
            disclaimer.text = it
        })
        notificationsViewModel.text_privacy.observe(viewLifecycleOwner, Observer {
            privacy.text = it
        })
        return root

    }

    override fun onStart() {
        super.onStart()
        userInfoMe.setOnClickListener{
            val intent = Intent(this.context, UserInfoActivity::class.java)
            startActivity(intent)
        }
        training_me.setOnClickListener {
            val intent = Intent(this.context, MainActivity::class.java)
            startActivity(intent)
        }
        disclaimer_me.setOnClickListener {
            val intent = Intent(this.context, DisclaimerActivity::class.java)
            startActivity(intent)
        }
        privacy_me.setOnClickListener {
            val intent = Intent(this.context, PrivacyActivity::class.java)
            startActivity(intent)
        }
        reminder_me.setOnClickListener {
            val intent = Intent(this.context, ReminderActivity::class.java)
            startActivity(intent)
        }
    }

}
