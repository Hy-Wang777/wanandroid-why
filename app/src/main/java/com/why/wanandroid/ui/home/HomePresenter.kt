package com.why.wanandroid.ui.home

import com.why.wanandroid.network.Network
import com.why.wanandroid.network.Apis
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/******************************************
 * 类描述：
 *
 * @author: why
 * @time: 2020/4/14 4:43 PM
 ******************************************/

class HomePresenter(private val view: HomeController.HomeView) : HomeController.HomePresenter {


    private val apis = Network.getNetWorkApi(Apis::class.java)
    override fun getHomeData() {

        GlobalScope.launch(Dispatchers.Main) {

            val banner = withContext(Dispatchers.IO) {
                apis.getHomeBanner()
            }
            val top = withContext(Dispatchers.IO) {
                apis.getHomeTop()
            }

            val list = withContext(Dispatchers.IO) {
                apis.getHomeList(0)
            }

            if (banner.errorCode == 0 && top.errorCode == 0 && list.errorCode == 0) {
                view.getHomeResult(banner.getData(), top.getData(), list.getData())
            }
        }
    }

    override fun getListMore(position: Int) {
        GlobalScope.launch(Dispatchers.Main) {

            val list = withContext(Dispatchers.IO) {
                apis.getHomeList(position)
            }

            if (list.errorCode == 0) {
                view.getListMoreResult(list.getData())
            }
        }
    }


    override fun unsubscribe() {
    }

}