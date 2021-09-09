package by.buglak.fitflow.ui.training.common

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import by.buglak.fitflow.MainActivity
import by.buglak.fitflow.R
import by.buglak.fitflow.ui.training.CommonInfo
import kotlinx.android.synthetic.main.activity_training_info.*

private const val NUM_PAGES = 3

open class TrainingInfoActivity() : FragmentActivity(), ViewPager.OnPageChangeListener {


    var type = TrainingType.instance.type
    private lateinit var mPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_training_info)

        mPager = findViewById(R.id.pagerTraining)
        CommonInfo.instance.context = this.applicationContext
        val pagerAdapter = ScreenSlidePagerAdapter(supportFragmentManager)
        mPager.adapter = pagerAdapter
        mPager.addOnPageChangeListener(this)

        goBackComBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        trainingTitle.text = CommonInfo.instance.titleText
        imageView.setImageResource(CommonInfo.instance.img)
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
                    EasySlidePageFragment()
                }
                1 -> {
                    NormalSlidePageFragment()
                }
                else -> {
                    HardSlidePageFragment()
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
                markerEasy.setImageResource(R.drawable.page_marker_item_sel)
                markerNormal.setImageResource(R.drawable.page_marker_item_unsel)
                markerHard.setImageResource(R.drawable.page_marker_item_unsel)
                CommonInfo.instance.trainingMode = "Beginner"
                CommonInfo.instance.time = 60
                trainingTextMode.text = CommonInfo.instance.trainingMode
            }
            1 -> {
                easyPoint.setImageResource(R.drawable.training_mode_point_on)
                normalPoint.setImageResource(R.drawable.training_mode_point_on)
                hardPoint.setImageResource(R.drawable.training_mode_point)
                markerEasy.setImageResource(R.drawable.page_marker_item_unsel)
                markerNormal.setImageResource(R.drawable.page_marker_item_sel)
                markerHard.setImageResource(R.drawable.page_marker_item_unsel)
                CommonInfo.instance.trainingMode = "Normal"
                CommonInfo.instance.time = 45
                trainingTextMode.text = CommonInfo.instance.trainingMode
            }
            2 -> {
                easyPoint.setImageResource(R.drawable.training_mode_point_on)
                normalPoint.setImageResource(R.drawable.training_mode_point_on)
                hardPoint.setImageResource(R.drawable.training_mode_point_on)
                markerEasy.setImageResource(R.drawable.page_marker_item_unsel)
                markerNormal.setImageResource(R.drawable.page_marker_item_unsel)
                markerHard.setImageResource(R.drawable.page_marker_item_sel)
                CommonInfo.instance.trainingMode = "Hard"
                CommonInfo.instance.time = 30
                trainingTextMode.text = CommonInfo.instance.trainingMode
            }
        }
    }

    override fun onPageSelected(position: Int) {

    }

}