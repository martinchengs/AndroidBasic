package com.martin.common.util

import android.app.Activity
import android.content.Context

/**
 * Created by Martin on 2017/8/6.
 * Hello World
 */
object UIUtil {

    /**获取屏幕宽度*/
    fun screenW(context: Context): Int = context.resources.displayMetrics.widthPixels

    /**获取屏幕高度*/
    fun screenH(context: Context): Int = context.resources.displayMetrics.heightPixels

    fun statusBarH(context: Context): Int {
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
        return context.resources.getDimensionPixelSize(resourceId)
    }
}