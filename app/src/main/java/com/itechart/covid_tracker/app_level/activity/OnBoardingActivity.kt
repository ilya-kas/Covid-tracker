package com.itechart.covid_tracker.app_level.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.itechart.covid_tracker.R
import com.itechart.covid_tracker.app_level.dagger.App
import com.itechart.covid_tracker.model.FilledCountriesProvider
import com.itechart.covid_tracker.screens.settings.SettingsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

internal val pages = intArrayOf( //layouts of pages of intro
    R.layout.onboard_page_1,
    R.layout.onboard_page_2,
    R.layout.onboard_page_3
)

class OnBoardingActivity: AppCompatActivity(), View.OnClickListener {
    private var myViewPagerAdapter: MyViewPagerAdapter? = null
    private lateinit var viewPager: ViewPager
    private var dotsLayout: LinearLayout? = null
    private lateinit var dots: Array<TextView?>
    private lateinit var bNext: Button
    private lateinit var bSkip: Button
    private lateinit var bPrev: Button

    private var flag = false //fag that everything is loaded to move to MainActivity

    @Inject
    lateinit var filledCountriesProvider: FilledCountriesProvider
    @Inject
    lateinit var settingsViewModel: SettingsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)
        App.appComponent.inject(this)

        viewPager = findViewById(R.id.vp_introduction)
        dotsLayout = findViewById(R.id.ll_dots)
        bNext = findViewById(R.id.b_next)
        bSkip = findViewById(R.id.b_skip)
        bPrev = findViewById(R.id.b_prev)

        setBottomDots(0)
        myViewPagerAdapter = MyViewPagerAdapter()
        viewPager.adapter = myViewPagerAdapter
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener)
        bNext.setOnClickListener(this)
        bSkip.setOnClickListener(this)
        bPrev.setOnClickListener(this)

        bNext.text = getString(R.string.next)
        bSkip.visibility = View.VISIBLE
        bPrev.visibility = View.GONE

        startPreloading()
    }

    private fun startPreloading(){
        GlobalScope.launch(Dispatchers.IO) {
            filledCountriesProvider.preload()
            flag = true
        }
    }

    private fun end(){
        GlobalScope.launch {
            while (!flag) Thread.yield()
            startActivity(Intent(this@OnBoardingActivity, MainActivity::class.java))
            finish()
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.b_next -> {
                if (viewPager.currentItem + 1 == pages.size)
                    end()
                viewPager.currentItem = viewPager.currentItem + 1
            }
            R.id.b_skip -> end()
            R.id.b_prev -> viewPager.currentItem = viewPager.currentItem - 1
        }
    }

    private fun setBottomDots(currentPage: Int) {
        dots = arrayOfNulls(pages.size)
        dotsLayout!!.removeAllViews()
        for (i in dots.indices) {
            dots[i] = TextView(this)
            dots[i]!!.text = Html.fromHtml("&#8226;")
            dots[i]!!.textSize = 35f
            dots[i]!!.setTextColor(Color.rgb(50,50,50))
            dotsLayout!!.addView(dots[i])
        }
        dots[currentPage]!!.setTextColor(resources.getColor(R.color.addiction))
    }

    private var viewPagerPageChangeListener: OnPageChangeListener = object : OnPageChangeListener {
        override fun onPageSelected(position: Int) {
            setBottomDots(position)

            // changing the next button text 'NEXT' / 'GOT IT'
            if (position == pages.size - 1) {
                // last page. make button text to GOT IT
                bNext.text = "Get started"
                bSkip.visibility = View.GONE
                bPrev.visibility = View.VISIBLE
            } else   {// still pages are left
                bNext.text = getString(R.string.next)
                bSkip.visibility = View.VISIBLE
                bPrev.visibility = View.VISIBLE
            }
            if (position == 0) {
                bNext.text = getString(R.string.next)
                bSkip.visibility = View.VISIBLE
                bPrev.visibility = View.GONE
            }
        }

        override fun onPageScrolled(arg0: Int, arg1: Float, arg2: Int) {}
        override fun onPageScrollStateChanged(arg0: Int) {}
    }

    /**
     * View pager adapter
     */
    class MyViewPagerAdapter : PagerAdapter() {
        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val view: View = LayoutInflater.from(App.appComponent.getContext()).inflate(pages[position], container, false)
            container.addView(view)
            return view
        }

        override fun getCount(): Int = pages.size

        override fun isViewFromObject(view: View, obj: Any): Boolean = view === obj

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            val view: View = `object` as View
            container.removeView(view)
        }
    }
}