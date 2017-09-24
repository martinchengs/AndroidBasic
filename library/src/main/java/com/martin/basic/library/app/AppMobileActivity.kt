/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.martin.basic.library.app

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.Toast
import com.martin.basic.library.R
import com.martin.basic.library.ex.routerTo
import com.martin.basic.library.util.DialogUtil

/**
 * by Martin
 * at 2017/9/24 23:22
 */
abstract class AppMobileActivity : AppCompatActivity(), IView {
    private var mToolbar: ToolbarWrapper? = null
    private var toast: Toast? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (dispatchOnCreate(savedInstanceState)) return
        beforeSetContentView(savedInstanceState)
        bindContentView()
        afterBindContentView()
        bindStatusBar()
        bindToolbar()
        bindView()
        bindData()
        bindEvent()
    }

    protected open fun dispatchOnCreate(savedInstanceState: Bundle?) = false

    protected open fun beforeSetContentView(savedInstanceState: Bundle?) {

    }

    protected open fun bindContentView() {
        setContentView(bindContentViewId())
    }

    protected open fun afterBindContentView() {

    }

    protected open fun bindStatusBar() {

    }

    protected open fun bindToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar?.let {
            mToolbar = ToolbarWrapper(this, toolbar)
            val backRes = AppContext.getResourceId("icon_app_back", ResourceType.DRAWABLE)
            if (backRes > 0) {
                mToolbar?.setBackIcon(backRes)
            }
        }
    }

    abstract fun bindView()

    abstract fun bindData()

    abstract fun bindEvent()

    abstract fun bindContentViewId(): Int


    override fun showToast(message: String, length: Int) {
        if (toast == null) {
            toast = Toast.makeText(this, message, length)
            toast?.show()
        } else {
            toast?.setText(message)
            toast?.show()
        }
    }

    fun getToolbar(): ToolbarWrapper? {
        return mToolbar
    }

    override fun <T : Activity> simpleTo(clazz: Class<T>) {
        routerTo(clazz)
    }

    override fun showLoading(message: String, cancelable: Boolean): AlertDialog {
        return DialogUtil.showLoadingDialog(this, message, cancelable)
    }

    override fun hideDialog(dialog: Dialog?) {
        DialogUtil.hide(dialog)
    }
}