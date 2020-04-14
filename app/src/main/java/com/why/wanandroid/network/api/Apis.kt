package com.why.wanandroid.network.api

import com.why.wanandroid.network.CommonResponse
import com.why.wanandroid.network.model.HomeBannerData
import com.why.wanandroid.network.model.HomeListData
import com.why.wanandroid.network.model.HomeTopData
import retrofit2.http.GET
import retrofit2.http.Path

/******************************************
 * 类描述：
 *
 * @author: why
 * @time: 2020/4/14 1:39 PM
 ******************************************/

interface Apis {

    companion object {
        const val BASE_URL = "https://www.wanandroid.com"
    }

    @GET("banner/json")
    suspend fun getHomeBanner(): CommonResponse<List<HomeBannerData>>

    @GET("article/top/json")
    suspend fun getHomeTop(): CommonResponse<List<HomeTopData>>

    @GET("article/list/{position}/json")
    suspend fun getHomeList(@Path("position") position: Int): CommonResponse<HomeListData>
}