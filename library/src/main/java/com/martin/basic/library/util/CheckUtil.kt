package com.martin.basic.library.util

/**
 * Created by Martin on 2017/9/17.
 * Hello World
 */
object CheckUtil {

    fun notNullException(checkArgs: Any?, errorMsg: String = "the args must not be null.") {
        if (checkArgs == null) {
            throw NullPointerException(errorMsg)
        }
    }

    fun stateBadException(checkArgs: Any?, errorMsg: String = "the args must not be null.") {
        if (checkArgs == null) {
            throw IllegalStateException(errorMsg)
        }
    }

    fun isNull(obj: Any?): Boolean {
        return obj == null
    }

    fun isNotNull(obj: Any?): Boolean {
        return !isNull(obj)
    }
}