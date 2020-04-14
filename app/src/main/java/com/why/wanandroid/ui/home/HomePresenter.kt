package com.why.wanandroid.ui.home

import com.why.wanandroid.network.Network
import com.why.wanandroid.network.Result
import com.why.wanandroid.network.api.Apis
import com.why.wanandroid.network.repository.HomeRepository
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


    override fun getHomeData() {

        GlobalScope.launch(Dispatchers.Main) {
            val banner = withContext(Dispatchers.IO) {
                HomeRepository(Network.getNetWorkApi(Apis::class.java)).getBanner()
            }

            val top = withContext(Dispatchers.IO) {
                HomeRepository(Network.getNetWorkApi(Apis::class.java)).getTopData()
            }

            val list = withContext(Dispatchers.IO) {
                HomeRepository(Network.getNetWorkApi(Apis::class.java)).getListData()
            }

            if (banner is Result.Success && top is Result.Success && list is Result.Success) {
                view.getHomeResult(banner.data, top.data, list.data)
            }
        }
    }

    override fun unsubscribe() {
    }

}