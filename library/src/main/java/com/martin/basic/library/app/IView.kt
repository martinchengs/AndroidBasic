package com.martin.basic.library.app

import android.app.Dialog
import android.support.v7.app.AlertDialog
import android.widget.Toast

/**
 * Created by Martin on 2017/8/13.
 * Hello World
 */
interface IView {

    fun showToast(message: String, length: Int = Toast.LENGTH_SHORT)

    fun showLoading(message: String = "正在加载...", cancelable: Boolean = true): AlertDialog

    fun hideDialog(dialog: Dialog?)
}