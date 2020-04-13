package com.why.wanandroid.ui.main

import androidx.fragment.app.Fragment
import com.why.wanandroid.R
import com.why.wanandroid.base.BaseActivity
import com.why.wanandroid.ui.home.HomeFragment
import com.why.wanandroid.ui.my.MyFragment
import com.why.wanandroid.ui.project.ProjectFragment
import com.why.wanandroid.ui.system.SystemFragment
import kotlinx.android.synthetic.main.activity_main.*

/******************************************
 * 类描述：
 *
 * @author: why
 * @time: 2020/4/13 3:38 PM
 ******************************************/
class MainActivity : BaseActivity() {


    private val fragments = arrayListOf<Fragment>()
    private var lastFragment = 0
    override fun getContentViewId(): Int = R.layout.activity_main


    override fun initView() {

        fragments.add(HomeFragment())
        fragments.add(ProjectFragment())
        fragments.add(SystemFragment())
        fragments.add(MyFragment())

        changeFragment(0, 0)

        nav_view.setOnNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.navigation_home -> {
                    if (lastFragment != 0) {
                        changeFragment(lastFragment, 0)
                        lastFragment = 0
                    }
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.navigation_project -> {
                    if (lastFragment != 1) {
                        changeFragment(lastFragment, 1)
                        lastFragment = 1
                    }
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_system -> {
                    if (lastFragment != 2) {
                        changeFragment(lastFragment, 2)
                        lastFragment = 2
                    }
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_my -> {
                    if (lastFragment != 3) {
                        changeFragment(lastFragment, 3)
                        lastFragment = 3
                    }
                    return@setOnNavigationItemSelectedListener true
                }
            }
            return@setOnNavigationItemSelectedListener false
        }
    }


    private fun changeFragment(lastFragment: Int, index: Int) {
        val ta = supportFragmentManager.beginTransaction()
        ta.hide(fragments[lastFragment])
        if (!fragments[index].isAdded) {
            ta.add(R.id.frame_layout_main, fragments[index])
        }

        ta.show(fragments[index]).commitAllowingStateLoss()
    }
}
