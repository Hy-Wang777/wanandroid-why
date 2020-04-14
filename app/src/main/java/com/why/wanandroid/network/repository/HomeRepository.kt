package com.why.wanandroid.network.repository

import com.why.wanandroid.network.BaseRepository
import com.why.wanandroid.network.Result
import com.why.wanandroid.network.api.Apis
import com.why.wanandroid.network.model.HomeBannerData
import com.why.wanandroid.network.model.HomeListData
import com.why.wanandroid.network.model.HomeTopData

/******************************************
 * 类描述：
 *
 * @author: why
 * @time: 2020/4/14 5:26 PM
 ******************************************/

class HomeRepository(private val apis: Apis) : BaseRepository() {
    suspend fun getBanner(): Result<List<HomeBannerData>> {
        return safeApiCall(call = { requestBanner() }, errorMessage = "错误")
    }

    private suspend fun requestBanner(): Result<List<HomeBannerData>> {
        val response = apis.getHomeBanner()
        return executeResponse(response)
    }

    suspend fun getTopData(): Result<List<HomeTopData>> {
        return safeApiCall(call = { requestTop() }, errorMessage = "错误")
    }

    private suspend fun requestTop(): Result<List<HomeTopData>> {
        val response = apis.getHomeTop()
        return executeResponse(response)
    }

    suspend fun getListData(): Result<HomeListData> {
        return safeApiCall(call = { requestList() }, errorMessage = "错误")
    }

    private suspend fun requestList(): Result<HomeListData> {
        val response = apis.getHomeList(0)
        return executeResponse(response)
    }
}
