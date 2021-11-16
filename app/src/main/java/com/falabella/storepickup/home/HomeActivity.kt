package com.falabella.storepickup.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import com.falabella.storepickup.R
import com.falabella.storepickup.databinding.ActivityHomeBinding
import android.view.Menu
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.falabella.storepickup.ui.storeconfig.StoreConfigurationActivity
import com.falabella.storepickup.utils.OrderConstants

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
            (tabView.findViewById<ImageView>(R.id.tabIconIv) as ImageView).setImageDrawable((ContextCompat.getDrawable(this, R.drawable.ic_pending)))
            (tabView.findViewById<TextView>(R.id.tabTitleTv) as TextView).text = OrderConstants.UPCOMING
            it.setCustomView(tabView)
        }
        tabs.getTabAt(1)?.let {
            val tabView = LayoutInflater.from(this).inflate(R.layout.home_tab_item, null)
            (tabView.findViewById<ImageView>(R.id.tabIconIv) as ImageView).setImageDrawable((ContextCompat.getDrawable(this, R.drawable.ic_completed)))
            (tabView.findViewById<TextView>(R.id.tabTitleTv) as TextView).text = OrderConstants.COMPLETED
            it.setCustomView(tabView)
        }
        //click listener
        binding.appBar.ivSettings.setOnClickListener {
            val intent = Intent(this@HomeActivity, StoreConfigurationActivity::class.java)
            startActivity(intent)
        }
    }

}