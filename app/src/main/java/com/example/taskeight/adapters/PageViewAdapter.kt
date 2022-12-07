package com.example.taskeight.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter
import com.example.taskeight.fragments.HomeFragment
import com.example.taskeight.fragments.ItemsFragment

class PageViewAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {


    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                HomeFragment()
            }

            1 -> {
                ItemsFragment()
            }
            else -> getItem(position)
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getItemPosition(`object`: Any): Int {
        return POSITION_NONE
    }

    override fun getPageTitle(position: Int): CharSequence? {

        when (position) {
            0 -> {
                return "Home"
            }
            1 -> {
                return "Items"
            }
        }
        return super.getPageTitle(position)
    }

}