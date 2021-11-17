package com.falabella.storepickup.home

import android.content.Intent
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.falabella.storepickup.R
import com.falabella.storepickup.databinding.ActivityHomeBinding
import com.falabella.storepickup.ui.storeconfig.StoreConfigurationActivity
import com.google.android.material.tabs.TabLayout

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionsPagerAdapter = HomePagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        tabs.setupWithViewPager(viewPager)
        tabs.getTabAt(0)?.let {
            val tabView = LayoutInflater.from(this).inflate(R.layout.home_tab_item, null)
            (tabView.findViewById(R.id.tabIconIv) as ImageView).setImageDrawable(
                (ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_pending
                ))
            )
            (tabView.findViewById(R.id.tabTitleTv) as TextView).text =
                getString(R.string.tab_text_1)

            val colorFilter = it.view.findViewById<ImageView>(R.id.tabIconIv)?.setColorFilter(
                ContextCompat.getColor(this@HomeActivity, R.color.white),
                PorterDuff.Mode.SRC_IN
            )
            it.view.findViewById<TextView>(R.id.tabTitleTv)
                ?.setTextColor(ContextCompat.getColor(this@HomeActivity, R.color.white))

            it.view.findViewById<ImageView>(R.id.tabIconIv)?.setColorFilter(
                ContextCompat.getColor(this@HomeActivity, R.color.white),
                PorterDuff.Mode.SRC_IN
            )
            it.view.findViewById<TextView>(R.id.tabTitleTv)
                ?.setTextColor(ContextCompat.getColor(this@HomeActivity, R.color.white))

            it.setCustomView(tabView)
        }
        tabs.getTabAt(1)?.let {
            val tabView = LayoutInflater.from(this).inflate(R.layout.home_tab_item, null)
            (tabView.findViewById(R.id.tabIconIv) as ImageView).setImageDrawable(
                (ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_completed
                ))
            )
            (tabView.findViewById(R.id.tabTitleTv) as TextView).text =
                getString(R.string.tab_text_2)

            it.view.findViewById<ImageView>(R.id.tabIconIv)?.setColorFilter(
                ContextCompat.getColor(this@HomeActivity, R.color.purple_500),
                PorterDuff.Mode.SRC_IN
            )
            it.view.findViewById<TextView>(R.id.tabTitleTv)
                ?.setTextColor(ContextCompat.getColor(this@HomeActivity, R.color.purple_500))

            it.view.findViewById<ImageView>(R.id.tabIconIv)?.setColorFilter(
                ContextCompat.getColor(this@HomeActivity, R.color.purple_500),
                PorterDuff.Mode.SRC_IN
            )
            it.view.findViewById<TextView>(R.id.tabTitleTv)
                ?.setTextColor(ContextCompat.getColor(this@HomeActivity, R.color.purple_500))

            it.setCustomView(tabView)
        }
        //click listener
        binding.appBar.ivSettings.setOnClickListener {
            val intent = Intent(this@HomeActivity, StoreConfigurationActivity::class.java)
            startActivity(intent)
        }

        binding.tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.view?.findViewById<ImageView>(R.id.tabIconIv)?.setColorFilter(
                    ContextCompat.getColor(this@HomeActivity, R.color.white),
                    PorterDuff.Mode.SRC_IN
                )
                tab?.view?.findViewById<TextView>(R.id.tabTitleTv)
                    ?.setTextColor(ContextCompat.getColor(this@HomeActivity, R.color.white))
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tab?.view?.findViewById<ImageView>(R.id.tabIconIv)?.setColorFilter(
                    ContextCompat.getColor(this@HomeActivity, R.color.purple_500),
                    PorterDuff.Mode.SRC_IN
                )
                tab?.view?.findViewById<TextView>(R.id.tabTitleTv)
                    ?.setTextColor(ContextCompat.getColor(this@HomeActivity, R.color.purple_500))
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
    }

}