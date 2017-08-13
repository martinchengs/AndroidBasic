package com.martin.basic.library.app

import android.widget.Toast

/**
 * Created by Martin on 2017/8/13.
 * Hello World
 */
interface IView {

    fun showToast(message: String, length: Int = Toast.LENGTH_SHORT)

    fun showLoading(message: String, cancelable: Boolean = true): ILoadingView
}