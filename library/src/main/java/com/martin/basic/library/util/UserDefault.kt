package com.martin.common.util;

import android.content.Context

/**
 * Created by 44269 on 2017/8/5.
 * Hello World
 */
object UserDefault {
    private lateinit var mUserDefault: UserDefaultBase
    private lateinit var mUserKeep: UserDefaultBase

    @Synchronized
    fun init(context: Context) {
        mUserDefault = UserDefaultBase(context.applicationContext.getSharedPreferences("user_default_t", Context.MODE_PRIVATE))
        mUserKeep = UserDefaultBase(context.applicationContext.getSharedPreferences("user_default_k", Context.MODE_PRIVATE))
    }

    fun setToken(token: String) = mUserDefault.apply("token", token)

    fun getToken() = mUserDefault.getString("token")
}
