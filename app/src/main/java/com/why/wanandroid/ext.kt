package com.why.wanandroid

import android.app.Activity
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.why.wanandroid.base.BaseFragment
import com.youth.banner.adapter.BannerAdapter
import java.util.*

/******************************************
 * 类描述：
 *
 * @author: why
 * @time: 2020/4/16 3:20 PM
 ******************************************/

private val fragmentStack: Stack<Pair<String, Fragment>> by lazy { Stack<Pair<String, Fragment>>() }

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction().func().commitAllowingStateLoss()
}

fun FragmentActivity.showFragment(fragments: ArrayList<Fragment>, lastFragment: Int, index: Int) {
    supportFragmentManager.inTransaction {
        hide(fragments[lastFragment])
        if (!fragments[index].isAdded) {
            add(R.id.frame_layout_main, fragments[index])
        }
        show(fragments[index])
    }
}

fun Fragment.showFragment(fragment: Fragment, tag: String) {
    parentFragmentManager.beginTransaction().add(R.id.frame_layout_main, fragment, tag).addToBackStack(tag).commitAllowingStateLoss()
    pushFragment(fragment, tag)
}

fun Fragment.closeFragment() {

    parentFragmentManager.let {
        val beginTransaction = it.beginTransaction()

        val size = fragmentStack.size
        if (size <= 0) return

        if (size > 1) {
            beginTransaction.show(fragmentStack[size - 2].second).remove(fragmentStack[size - 1].second).commitAllowingStateLoss()
        } else {
            beginTransaction.remove(fragmentStack[size - 1].second).commitAllowingStateLoss()
        }
        it.executePendingTransactions()
        popFragment()
    }
}

private fun pushFragment(fragment: Fragment, tag: String) {
    val temp = Pair<String, Fragment>(tag, fragment)
    fragmentStack.push(temp)
}

private fun popFragment() {
    for (i in 0 until 1) {
        if (fragmentStack.size > 0) {
            fragmentStack.pop()
        }
    }
}



fun onFragmentKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
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


