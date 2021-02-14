package com.joenjogu.nexflix.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.joenjogu.nexflix.R
import com.joenjogu.nexflix.adapters.FragmentAdapter

class ViewPagerFragment : Fragment() {

    companion object {
        val TAG: String = ViewPagerFragment::class.java.name
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val toolbar = view?.findViewById<Toolbar>(R.id.menu_app_bar)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)

        return inflater.inflate(R.layout.fragment_viewpager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val viewPager = view.findViewById<ViewPager2>(R.id.viewpager)
        val tabLayout = view.findViewById<TabLayout>(R.id.tab_layout)


        val fragmentAdapter = FragmentAdapter(childFragmentManager, lifecycle)
        viewPager.adapter = fragmentAdapter
        viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        TabLayoutMediator(tabLayout, viewPager){ tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.popular)
                1 -> tab.text = getString(R.string.latest)
            }
            viewPager.setCurrentItem(0, true)
        }.attach()


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_search -> {
                // search function
                return true
            }
            R.id.menu_settings -> {
                Log.d(TAG, "onOptionsItemSelected: Settings Button Clicked")
                val intent = Intent(context, SettingsActivity::class.java)
                startActivity(intent)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
        return false
    }
}