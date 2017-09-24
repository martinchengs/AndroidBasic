package com.martin.basic.library.app

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.os.Handler
import android.os.Looper
import android.support.annotation.*
import android.support.v4.content.ContextCompat
import android.widget.Toast
import com.martin.basic.library.util.CheckUtil
import com.martin.basic.library.util.UIUtil
import org.jetbrains.annotations.NotNull
import java.io.InputStream


class AppContext private constructor(@NotNull private val context: Context) {
    private val handler: Handler by lazy {
        Handler(Looper.getMainLooper())
    }

    private val runList: MutableMap<String, AppRunnable> by lazy { mutableMapOf<String, AppRunnable>() }

    companion object {
        private var appContext: AppContext? = null
        fun init(context: Context): AppContext {
            if (null == appContext) {
                appContext = AppContext(context.applicationContext)

            }
            return appContext!!
        }

        fun get(): AppContext {
            CheckUtil.stateBadException(appContext, "you must init first.")
            return appContext!!
        }

        @CheckResult
        fun getContext(): Context {
            CheckUtil.stateBadException(appContext, "you must init first.")
            return get().context
        }

        @CheckResult
        fun getDrawable(@DrawableRes res: Int): Drawable {
            return ContextCompat.getDrawable(getContext(), res)
        }

        @CheckResult
        fun getColor(@ColorRes res: Int): Int {
            return ContextCompat.getColor(getContext(), res)
        }

        @CheckResult
        fun getColorStateList(@ColorRes res: Int): ColorStateList? {
            return ContextCompat.getColorStateList(getContext(), res);
        }

        @CheckResult
        fun getString(@StringRes res: Int): String {
            return getContext().getString(res)
        }

        @CheckResult
        fun getString(@StringRes res: Int, vararg args: Any): String {
            return getContext().getString(res, args)
        }

        @CheckResult
        fun getDimens(@DimenRes res: Int): Int {
            return getContext().resources.getDimensionPixelSize(res)
        }

        @CheckResult
        fun openRaw(@RawRes res: Int): InputStream {
            return getContext().resources.openRawResource(res)
        }

        @CheckResult
        fun getResourceId(@NotNull resName: String, @NotNull type: ResourceType): Int {
            return getContext().resources.getIdentifier(resName, type.type, getContext().packageName)
        }

        fun toast(message: String, length: Int = Toast.LENGTH_SHORT) {
            Toast.makeText(getContext(), message, length).show()
        }


        fun toast(@StringRes res: Int, length: Int = Toast.LENGTH_SHORT) {
            Toast.makeText(getContext(), res, length).show()
        }

        @CheckResult
        fun isMainThread(): Boolean {
            return Looper.myLooper() == Looper.getMainLooper()
        }

        @CheckResult
        fun getScreenWidth(): Int {
            return UIUtil.screenW(getContext())
        }

        @CheckResult
        fun getScreenHeight(): Int {
            return UIUtil.screenH(getContext())
        }

        @CheckResult
        fun getScreenSize(): IntArray {
            return UIUtil.getScreenSize(getContext())
        }

        @CheckResult
        fun getAppName(): String {
            return getContext().applicationInfo.loadLabel(getContext().packageManager).toString()
        }

        @CheckResult
        fun getAppIcon(): Drawable {
            return getContext().applicationInfo.loadIcon(getContext().packageManager)
        }

        @CheckResult
        fun getVersionCode(): Int {
            return getContext().packageManager.getPackageInfo(getContext().packageName, 0).versionCode
        }

        @CheckResult
        fun getVersionName(): String {
            return getContext().packageManager.getPackageInfo(getContext().packageName, 0).versionName
        }

        @Suppress("UNCHECKED_CAST")
        @CheckResult
        fun <T> getSystemService(@NonNull serviceName: String): T {
            return getContext().getSystemService(serviceName) as T
        }
    }

    fun post(run: Runnable) {
        handler.post(run)
    }

    fun postDelay(run: Runnable, delayMillis: Long) {
        handler.postDelayed(run, delayMillis)
    }

    fun removeCallbacksAndMessages() {
        handler.removeCallbacksAndMessages(null)
    }

    fun post(key: String, value: Runnable) {
        val runnable = AppRunnable(key, value)
        handler.post(runnable)
    }

    fun removeRunnable(key: String) {
        val value = runList[key]
        if (value != null) {
            handler.removeCallbacks(value)
        }
    }

    private inner class AppRunnable(val key: String, val realRun: Runnable) : Runnable {
        init {
            AppContext.get().runList.put(key, this)
        }

        override fun run() {
            realRun.run()
            AppContext.get().runList.remove(key)
        }
    }

    fun destroy() {
        runList.clear()
        removeCallbacksAndMessages()
    }
}