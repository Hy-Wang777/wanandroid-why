package com.why.wanandroid.base

import android.view.KeyEvent
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.why.wanandroid.R
import com.why.wanandroid.utils.Constants
import org.greenrobot.eventbus.EventBus
import java.util.*

/******************************************
 * 类描述：
 *
 * @author: why
 * @time: 2020/4/15 2:52 PM
 ******************************************/

object FragmentControl {

    private lateinit var mFragmentManager: FragmentManager
    private val fragmentStack: Stack<Pair<String, Fragment>> by lazy { Stack<Pair<String, Fragment>>() }

    fun showFragment(fragmentManager: FragmentManager, fragment: Fragment, tag: String) {
        mFragmentManager = fragmentManager
        mFragmentManager.beginTransaction().add(R.id.frame_layout_main, fragment).addToBackStack(tag).commitAllowingStateLoss()

        pushFragment(fragment, tag)
    }

    private fun pushFragment(fragment: Fragment, tag: String) {
        val temp = Pair<String, Fragment>(tag, fragment)
        fragmentStack.push(temp)
    }

    fun closeFragment() {

        val size = fragmentStack.size
        if (size <= 0) return

        val ta = mFragmentManager.beginTransaction()
        if (size > 1) {
            ta.show(fragmentStack[size - 2].second).remove(fragmentStack[size - 1].second).commitAllowingStateLoss()
        } else {
            ta.remove(fragmentStack[size - 1].second).commitAllowingStateLoss()
        }
        popFragment()
        notifiMainVisible()
    }

    private fun popFragment() {
        for (i in 0 until 1) {
            if (fragmentStack.size > 0) {
                fragmentStack.pop()
            }
        }
    }


    private fun notifiMainVisible() {
        val params = HashMap<String, String>()
        params["action"] = Constants.ACTION_VISIBLE_MAIN_INDEX
        EventBus.getDefault().post(params)
    }

    fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        val topfrag: Fragment? = getTopFragment()
        var ret = false
        if (topfrag != null && topfrag is BaseFragment) {
            ret = topfrag.onKeyDown(keyCode, event)
        }
        return ret
    }

    //获取栈顶的fragment
    private fun getTopFragment(): Fragment? {
        return if (fragmentStack.size > 0) {
            fragmentStack[fragmentStack.size - 1].second
        } else null
    }

}