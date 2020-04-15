package com.why.wanandroid.model

/******************************************
 * 类描述：
 *
 * @author: why
 * @time: 2020/4/14 4:52 PM
 ******************************************/

data class HomeBannerData(
    val desc: String,
    val id: Int,
    val imagePath: String,
    val isVisible: Int,
    val order: Int,
    val title: String,
    val type: Int,
    val url: String
)