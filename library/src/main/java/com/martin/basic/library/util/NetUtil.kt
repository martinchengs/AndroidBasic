package com.martin.common.util

import android.content.Context
import android.net.ConnectivityManager

/**
 * Created by Martin on 2017/8/6.
 * Hello World
 */
class NetUtil {
    fun has(context: Context): Boolean {
        val manager: ConnectivityManager? = context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
        manager?.let {
            val info = manager.activeNetworkInfo
            return info?.isAvailable ?: false
        }
        return false
    }
}