package by.buglak.fitflow.ui.training.situps

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import by.buglak.fitflow.MainActivity
import by.buglak.fitflow.R
import kotlinx.android.synthetic.main.activity_situps_info.*


private const val NUM_PAGES = 3

open class SitupsInfoActivity : FragmentActivity(), OnPageChangeListener {

    private lateinit var mPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_situps_info)

        mPager = findViewById(R.id.pagerSitups)

        val pagerAdapter = ScreenSlidePagerAdapter(supportFragmentManager)
        mPager.adapter = pagerAdapter
        mPager.addOnPageChangeListener(this)

        goBackBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        if (mPager.currentItem == 0) {
            super.onBackPressed()
        } else {
            mPager.currentItem = mPager.currentItem - 1
        }
    }

    private inner class ScreenSlidePagerAdapter(fm: FragmentManager) :
        FragmentStatePagerAdapter(fm) {

        override fun getCount(): Int = NUM_PAGES

        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> {
                    EasySitupsSlidePageFragment()
                }
                1 -> {
                    NormalSitupsSlidePageFragment()
                }
                else -> {
                    HardSitupsSlidePageFragment()
                }
            }
        }
    }

    override fun onPageScrollStateChanged(state: Int) {

    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        when (position) {
            0 -> {
                easyPoint.setImageResource(R.drawable.training_mode_point_on)
                normalPoint.setImageResource(R.drawable.training_mode_point)
                hardPoint.setImageResource(R.drawable.training_mode_point)
                situpsTextMode.text = "Beginner"
            }
            1 -> {
                easyPoint.setImageResource(R.drawable.training_mode_point_on)
                normalPoint.setImageResource(R.drawable.training_mode_point_on)
                hardPoint.setImageResource(R.drawable.training_mode_point)
                situpsTextMode.text = "Normal"
            }
            2 -> {
                easyPoint.setImageResource(R.drawable.training_mode_point_on)
                normalPoint.setImageResource(R.drawable.training_mode_point_on)
                hardPoint.setImageResource(R.drawable.training_mode_point_on)
                situpsTextMode.text = "Hard"
            }
        }
        }

    override fun onPageSelected(position: Int) {

    }

}



