package com.why.wanandroid.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.greenrobot.eventbus.EventBus

/******************************************
 * 类描述：
 *
 * @author: why
 * @time: 2020/4/13 3:38 PM
 ******************************************/

abstract class BaseActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (isRegisteredEventBus()) {
            if (!EventBus.getDefault().isRegistered(this)) {
                EventBus.getDefault().register(this)
            }
        }

        setContentView(getContentViewId())

        initView()
        initData()
    }

    open fun initData() {

    }

    open fun initView() {

    }

    override fun onDestroy() {
        super.onDestroy()
        if (isRegisteredEventBus()) {
            if (EventBus.getDefault().isRegistered(this)) {
                EventBus.getDefault().unregister(this)
            }
        }
    }

    override fun onBackPressed() {

    }


    open fun isRegisteredEventBus(): Boolean = false

    abstract fun getContentViewId(): Int
}