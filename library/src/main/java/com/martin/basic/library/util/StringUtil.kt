package com.martin.basic.library.util

import android.text.TextUtils

/**
 * Created by Martin on 2017/8/7.
 * Hello World
 */
object StringUtil {

    fun isEmpty(str: String?): Boolean {
        return TextUtils.isEmpty(str)
    }

    fun isNotEmpty(str: String?): Boolean {
        return !isEmpty(str)
    }

    fun isEmptyToTrim(str: String?): Boolean {
        if (str == null) return true
        return TextUtils.isEmpty(str.trim())
    }

    fun isBlank(str: String?): Boolean {
        if (str == null) return true
        for (c in str) {
            if (!c.isWhitespace()) return false
        }
        return true
    }

    fun isNotBlank(str: String?): Boolean = !isBlank(str)

    fun trim(str: String?): String? {
        if (str == null) return null
        return str.trim()
    }
}