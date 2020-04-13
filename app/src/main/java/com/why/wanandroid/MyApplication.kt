package com.why.wanandroid

import android.app.Application

/******************************************
 * 类描述：
 *
 * @author: why
 * @time: 2020/4/13 3:39 PM
 ******************************************/

class MyApplication : Application() {

    companion object {
        val instance: MyApplication by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            MyApplication()
        }
    }

}
 