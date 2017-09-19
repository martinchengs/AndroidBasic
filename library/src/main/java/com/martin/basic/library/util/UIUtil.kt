package com.martin.basic.library.util

import android.app.Activity
import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager
import com.martin.basic.library.app.AppContext

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

    fun getScreenSize(context: Context): IntArray {
        val metrics = context.resources.displayMetrics
        return intArrayOf(metrics.widthPixels, metrics.heightPixels)
    }
}