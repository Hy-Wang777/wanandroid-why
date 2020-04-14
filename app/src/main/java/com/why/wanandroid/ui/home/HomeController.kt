package com.why.wanandroid.ui.home

import com.why.wanandroid.base.IBasePresenter
import com.why.wanandroid.base.IBaseView
import com.why.wanandroid.network.model.HomeBannerData
import com.why.wanandroid.network.model.HomeListData
import com.why.wanandroid.network.model.HomeTopData

/******************************************
 * 类描述：
 *
 * @author: why
 * @time: 2020/4/14 4:45 PM
 ******************************************/

class HomeController {

    interface HomeView : IBaseView<HomePresenter> {
        fun getHomeResult(banner: List<HomeBannerData>, top: List<HomeTopData>, home: HomeListData)
    }

    interface HomePresenter : IBasePresenter {
        fun getHomeData()
    }
}