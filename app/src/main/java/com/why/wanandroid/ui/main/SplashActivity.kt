package com.why.wanandroid.ui.main

import com.why.wanandroid.R
import com.why.wanandroid.base.BaseActivity
import com.why.wanandroid.ui.main.MainActivity
import kotlinx.coroutines.*
import org.jetbrains.anko.startActivity

/******************************************
 * 类描述：
 *
 * @author: why
 * @time: 2020/4/13 5:00 PM
 ******************************************/

class SplashActivity : BaseActivity() {
    override fun getContentViewId(): Int = R.layout.activity_splash


    override fun initView() {
    }

    override fun initData() {

        GlobalScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                delay(3000)
            }

            startActivity<MainActivity>()
            finish()
        }
    }
}