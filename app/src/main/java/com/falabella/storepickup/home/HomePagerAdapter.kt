package com.falabella.storepickup.home

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.falabella.storepickup.orderlist.OrderListFragment

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class HomePagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    val fragments = listOf(
        OrderListFragment.newInstance(false),
        OrderListFragment.newInstance(true)
    )

    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return fragments[position]
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return fragments.size
    }
}