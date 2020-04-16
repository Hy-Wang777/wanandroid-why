package com.why.wanandroid.ui.main

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

/******************************************
 * 类描述：
 *
 * @author: why
 * @time: 2020/4/16 2:19 PM
 ******************************************/

class MainVp2Adapter constructor(fragmentActivity: MainActivity, private val fragments: ArrayList<Fragment>) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

}
