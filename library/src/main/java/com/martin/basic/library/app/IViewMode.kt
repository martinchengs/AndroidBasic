package com.martin.basic.library.app

import android.os.Bundle
import android.widget.Toast
import io.reactivex.disposables.Disposable

/**
 * Created by Martin on 2017/8/13.
 * Hello World
 */
interface IViewMode {
    fun onCreate(savedInstanceState: Bundle?)
    fun onDestroy()
    fun addDisposable(tag: String? = null, disposable: Disposable)
    fun removeDisposable(tag: String)
    fun useEventBus(): Boolean {
        return false
    }
}