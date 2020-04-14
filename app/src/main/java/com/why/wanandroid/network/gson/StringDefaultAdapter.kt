package com.why.wanandroid.network.gson

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import java.lang.reflect.Type

/******************************************
 * 类描述：
 *
 * @author: why
 * @time: 2020/4/13 6:40 PM
 ******************************************/

class StringDefaultAdapter : JsonDeserializer<String?> {
    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): String {
        if (json != null) {
            try {
                if (json.asString == "null") {
                    return ""
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return json.asString
        }
        return ""
    }
}