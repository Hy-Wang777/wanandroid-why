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

        setContentView(getContentViewId())
        if (isRegisteredEventBus()) {
            if (!EventBus.getDefault().isRegistered(this)) {
                EventBus.getDefault().isRegistered(this)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isRegisteredEventBus()) {
            if (EventBus.getDefault().isRegistered(this)) {
                EventBus.getDefault().unregister(this)
            }
        }
    }

    fun isRegisteredEventBus(): Boolean = false

    abstract fun getContentViewId(): Int
}