package com.why.wanandroid.network

/******************************************
 * 类描述：
 *
 * @author: why
 * @time: 2020/4/13 6:50 PM
 ******************************************/

class CommonResponse<V> : BaseResponse() {
    private var data: V? = null

    fun getData(): V? {
        return data
    }

    fun setData(data: V) {
        this.data = data
    }
}
