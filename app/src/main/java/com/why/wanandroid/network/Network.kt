package com.why.wanandroid.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.why.wanandroid.network.api.Apis
import com.why.wanandroid.network.gson.IntegerDefaultAdapter
import com.why.wanandroid.network.gson.StringDefaultAdapter
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/******************************************
 * 类描述：
 *
 * @author: why
 * @time: 2020/4/14 3:53 PM
 ******************************************/

object Network {

    private val okHttpClient by lazy {
        OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS).build()
    }

    private val gsonConverterFactory by lazy {
        GsonConverterFactory.create(buildGson())
    }

    private fun buildGson(): Gson = GsonBuilder().registerTypeAdapter(Integer::class.java, IntegerDefaultAdapter())
        .registerTypeAdapter(String::class.java, StringDefaultAdapter())
        .create()

    private val retrofit by lazy { Retrofit.Builder().baseUrl(Apis.BASE_URL).addConverterFactory(gsonConverterFactory).client(okHttpClient).build() }

    fun <T> getNetWorkApi(tClass: Class<T>?): T {
        return retrofit.create(tClass)
    }

}