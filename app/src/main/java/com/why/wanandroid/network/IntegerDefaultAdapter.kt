package com.why.wanandroid.network

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import java.lang.reflect.Type

/******************************************
 * 类描述：
 *
 * @author: why
 * @time: 2020/4/13 6:39 PM
 ******************************************/

class IntegerDefaultAdapter : JsonDeserializer<Int?> {
    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, typeOfT: Type?, context: JsonDeserializationContext?): Int {
        try {
            if (json.asString == "" || json.asString == "null") {
                return 0
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return json.asInt
    }
}
