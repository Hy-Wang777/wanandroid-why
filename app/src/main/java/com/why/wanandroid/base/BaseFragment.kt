package com.why.wanandroid.base

import android.os.Bundle
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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (isRegisteredEventBus()) {
            if (!EventBus.getDefault().isRegistered(this)) {
                EventBus.getDefault().isRegistered(this)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
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
            if (EventBus.getDefault().isRegistered(this)){
                EventBus.getDefault().unregister(this)
            }
        }
    }

   open fun isRegisteredEventBus(): Boolean = false

   open fun initView(view:View){

    }
   open fun initData(){

    }

    abstract fun getLayoutRes(): Int


}