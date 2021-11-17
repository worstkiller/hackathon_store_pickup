package com.falabella.storepickup.home

import android.content.Intent
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.falabella.storepickup.orderlist.SearchListener
import androidx.viewpager.widget.ViewPager
import com.falabella.storepickup.R
import com.falabella.storepickup.databinding.ActivityHomeBinding
import com.falabella.storepickup.firebase.StoreFirebaseManager
import com.falabella.storepickup.ui.storeconfig.StoreConfigurationActivity
import com.falabella.storepickup.utils.UiUtils
import com.google.android.material.tabs.TabLayout

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private var searchQuery: String? = null
    private lateinit var firebaseManager: StoreFirebaseManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseManager = StoreFirebaseManager()

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        listenForNewAppointments()
        val sectionsPagerAdapter = HomePagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        tabs.setupWithViewPager(viewPager)
        tabs.getTabAt(0)?.let {
            val tabView = LayoutInflater.from(this).inflate(R.layout.home_tab_item, null)
            val imageView = tabView.findViewById(R.id.tabIconIv) as ImageView
            val textView = tabView.findViewById(R.id.tabTitleTv) as TextView
            imageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_pending))
            textView.text = getString(R.string.tab_text_1)
            imageView.setColorFilter(
                ContextCompat.getColor(this@HomeActivity, R.color.white),
                PorterDuff.Mode.SRC_IN
            )
            textView.setTextColor(ContextCompat.getColor(this@HomeActivity, R.color.white))
            it.setCustomView(tabView)
        }
        tabs.getTabAt(1)?.let {
            val tabView = LayoutInflater.from(this).inflate(R.layout.home_tab_item, null)
            val imageView = tabView.findViewById(R.id.tabIconIv) as ImageView
            val textView = tabView.findViewById(R.id.tabTitleTv) as TextView

            imageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_completed))
            textView.text = getString(R.string.tab_text_2)
            imageView.setColorFilter(
                ContextCompat.getColor(this@HomeActivity, R.color.purple_500),
                PorterDuff.Mode.SRC_IN
            )
            textView.setTextColor(ContextCompat.getColor(this@HomeActivity, R.color.purple_200))
            it.setCustomView(tabView)
        }

        //click listener
        binding.appBar.ivSettings.setOnClickListener {
            val intent = Intent(this@HomeActivity, StoreConfigurationActivity::class.java)
            startActivity(intent)
        }

        binding.tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if(searchQuery.isNullOrBlank().not()) {
                    (sectionsPagerAdapter.fragments[tabs.selectedTabPosition] as? SearchListener)?.onSearchWithText(searchQuery)
                }
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

        binding.appBar.ordersSearch.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchQuery = newText
                (sectionsPagerAdapter.fragments[tabs.selectedTabPosition] as? SearchListener)?.onSearchWithText(newText)
                return false
            }

        })
    }

    private fun listenForNewAppointments() {
        firebaseManager.observeAppointmentsChanges(true) { data ->
            data?.let {
                UiUtils.showNotifications(
                    "Order Received :${it.orderNo}",
                    "From: ${data.customerName} - ${data.orderPrice}",
                    this
                )
            }
        }
    }

}