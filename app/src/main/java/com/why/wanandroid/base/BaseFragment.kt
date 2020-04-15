package com.why.wanandroid.base

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.greenrobot.eventbus.EventBus

/******************************************
 * 类描述：
 *
 * @author: why
 * @time: 2020/4/13 3:52 PM
 ******************************************/

abstract class BaseFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (isRegisteredEventBus()) {
            if (!EventBus.getDefault().isRegistered(this)) {
                EventBus.getDefault().register(this)
            }
        }
        return inflater.inflate(getLayoutRes(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        initData()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isRegisteredEventBus()) {
            if (EventBus.getDefault().isRegistered(this)) {
                EventBus.getDefault().unregister(this)
            }
        }
    }

    open fun isRegisteredEventBus(): Boolean = false

    open fun initView(view: View) {

    }

    open fun initData() {

    }

    abstract fun getLayoutRes(): Int

//    //默认返回false
//    open fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
//        closeFragment()
//        return true
//    }

    //默认返回false
    open fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return false
    }

    open fun closeFragment() {
        FragmentControl.closeFragment()
    }

}