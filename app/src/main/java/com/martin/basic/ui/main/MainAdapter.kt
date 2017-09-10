package com.martin.basic.ui.main

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.martin.basic.ui.main.home.HomeFragment

/**
 * Created by Martin on 2017/9/10.
 * Hello World
 */
class MainAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return HomeFragment()
    }

    override fun getCount(): Int {
        return 4
    }
}