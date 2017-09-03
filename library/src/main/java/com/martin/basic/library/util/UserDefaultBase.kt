package com.martin.common.util

import android.annotation.SuppressLint
import android.content.SharedPreferences

/**
 * Created by Martin on 2017/8/6.
 * Hello World
 */
class UserDefaultBase constructor(val mSharePfs: SharedPreferences) {
    @SuppressLint("ApplySharedPref")
    fun commit(key: String, value: Any?) {
        process(key, value).commit()
    }

    fun apply(key: String, value: Any?) {
        process(key, value).commit()
    }

    fun getString(key: String, defaultValue: String? = null): String? = mSharePfs.getString(key, defaultValue)

    fun getBoolean(key: String, defaultValue: Boolean = false) = mSharePfs.getBoolean(key, defaultValue)

    fun getInt(key: String, defaultValue: Int = 0) = mSharePfs.getInt(key, defaultValue)

    fun getFloat(key: String, defaultValue: Float = 0f) = mSharePfs.getFloat(key, defaultValue)

    fun getLong(key: String, defaultValue: Long = 0L) = mSharePfs.getLong(key, defaultValue)


    private fun process(key: String, value: Any?): SharedPreferences.Editor {
        var editor = mSharePfs.edit()
        editor = when (value) {
            value is String -> {
                editor.putString(key, value as String?)
            }
            value is Int -> {
                editor.putInt(key, value as Int)
            }

            value is Long -> {
                editor.putLong(key, value as Long)
            }

            value is Float -> {
                editor.putFloat(key, value as Float)
            }

            value is Boolean -> {
                editor.putBoolean(key, value as Boolean)
            }
            else -> {
                throw IllegalArgumentException("not support type")
            }
        }
        return editor
    }
}