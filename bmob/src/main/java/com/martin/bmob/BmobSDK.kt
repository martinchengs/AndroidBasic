package com.martin.bmob

import android.content.Context
import cn.bmob.v3.Bmob
import cn.bmob.v3.BmobConfig

/**
 * Created by Martin on 2017/8/28.
 * Hello World
 */
object BmobSDK {

    fun initialize(context: Context) {
        val cfg = BmobConfig.Builder(context)
                .setApplicationId("507234a0db79fe3898ee46bf5a430e7b")
                .setConnectTimeout(30)
                .setUploadBlockSize(1024 * 1024)
                .setFileExpiration(2500)
                .build()
        Bmob.initialize(cfg)
    }
}