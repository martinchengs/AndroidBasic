package com.martin.common.util

import android.app.Activity
import android.app.Application
import android.content.Context
import android.databinding.Observable
import android.databinding.ObservableField
import android.os.Bundle
import java.util.*

/**
 * Created by Martin on 2017/8/6.
 * Hello World
 */
object ActivityManager {
    private val activitys: LinkedList<Activity?> by lazy { LinkedList<Activity?>() }
    private var count: Int = 0
    private val fb: ObservableField<Boolean> = ObservableField(false)

    fun register(context: Context) {
        if (context is Application) {
            context.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
                override fun onActivityPaused(activity: Activity?) {

                }

                override fun onActivityResumed(activity: Activity?) {
                }

                override fun onActivityStarted(activity: Activity?) {
                    count++
                }

                override fun onActivityStopped(activity: Activity?) {
                    count--
                }

                override fun onActivityDestroyed(activity: Activity?) {
                }

                override fun onActivitySaveInstanceState(activity: Activity?, bundle: Bundle?) {
                }


                override fun onActivityCreated(activity: Activity?, bundle: Bundle?) {
                }

            })
        }
    }

    fun exit() {

    }

    fun isBackground() = count == 0

    fun isForeground() = count > 0


    interface AppListener {
        fun onForeground()

        fun onBackground()
    }
}