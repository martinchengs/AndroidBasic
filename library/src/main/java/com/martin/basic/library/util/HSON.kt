package com.martin.common.util

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Created by Martin on 2017/8/6.
 * Hello World
 */
object HSON {

    val gson: Gson by lazy {
        Gson()
    }

    inline fun <reified T> from(json: String, g: Gson = gson): T = g.fromJson(json, T::class.java)

    inline fun <reified T> fromT(json: String, g: Gson = gson): T? = g.fromJson<T>(json, object : TypeToken<T>() {}.type)

    fun to(obj: Any?, g: Gson = gson): String? = obj?.let { g.toJson(obj) }
}