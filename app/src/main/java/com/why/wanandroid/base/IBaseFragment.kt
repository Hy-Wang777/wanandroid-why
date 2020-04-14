package com.why.wanandroid.base

import android.os.Bundle

/******************************************
 * 类描述：
 *
 * @author: why
 * @time: 2020/4/13 4:16 PM
 ******************************************/

abstract class IBaseFragment<T : IBasePresenter> : BaseFragment(), IBaseView<T> {

    var presenter : T? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setPresenter(presenter)
    }
}