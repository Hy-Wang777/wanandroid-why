package com.why.wanandroid.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

/******************************************
 * 类描述：
 *
 * @author: why
 * @time: 2020/4/14 6:05 PM
 ******************************************/

object DateUtils {

    /**
     * 格式化时间
     * @param time 时间戳（毫秒）
     * @param pattern 格式（"yyyy-MM-dd HH:mm:ss"）
     * @return 格式化后的时间
     */
    fun getFormatDate(time: Long, pattern: String?): String? {
        val date = Date(time)
        @SuppressLint("SimpleDateFormat") val format = SimpleDateFormat(pattern)
        return format.format(date)
    }


}