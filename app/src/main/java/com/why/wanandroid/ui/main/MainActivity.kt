package com.why.wanandroid.ui.main

import android.view.KeyEvent
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import com.why.wanandroid.R
import com.why.wanandroid.showFragment
import com.why.wanandroid.base.BaseActivity
import com.why.wanandroid.onFragmentKeyDown
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
class MainActivity : BaseActivity(), RadioGroup.OnCheckedChangeListener {

    companion object {
        val instance = MainActivity()
    }

    private val fragments = arrayListOf<Fragment>()
    private var lastFragment = 0

    override fun getContentViewId(): Int = R.layout.activity_main

    override fun initView() {

        fragments.add(HomeFragment())
        fragments.add(ProjectFragment())
        fragments.add(SystemFragment())
        fragments.add(MyFragment())

        vp2_main.adapter = MainVp2Adapter(this, fragments)
        vp2_main.isUserInputEnabled = false

        radio_main_tab.setOnCheckedChangeListener(this)
    }


    private fun changeFragment(lastFragment: Int, index: Int) {
        showFragment(fragments, lastFragment, index)
        this.lastFragment = index
    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (onFragmentKeyDown(keyCode, event)) {
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        when (checkedId) {
            R.id.rb_main_tab_home -> changeFragment(lastFragment, 0)
            R.id.rb_main_tab_square -> changeFragment(lastFragment, 1)
            R.id.rb_main_tab_system -> changeFragment(lastFragment, 2)
            R.id.rb_main_tab_mine -> changeFragment(lastFragment, 3)
        }
    }
}
