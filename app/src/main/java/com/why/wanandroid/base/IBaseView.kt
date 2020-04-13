package com.why.wanandroid.base

/******************************************
 * 类描述：
 *
 * @author: why
 * @time: 2020/4/13 3:49 PM
 ******************************************/

interface IBaseView<T> {
    fun setPresenter(presenter: T?)
}