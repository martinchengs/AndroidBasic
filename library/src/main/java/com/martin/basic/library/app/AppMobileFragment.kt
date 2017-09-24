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
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.martin.basic.library.ex.routerTo
import com.martin.basic.library.util.DialogUtil

/**
 * by Martin
 * at 2017/9/24 23:22
 */
abstract class AppMobileFragment : Fragment(), IView {
    private var mToolbar: ToolbarWrapper? = null
    private var toast: Toast? = null
    private var mContext: Context? = null
    private var mContentView: View? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        this.mContext = context
        if (context is AppMobileActivity) {
            mToolbar = context.getToolbar()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (mContentView != null) {
            container?.removeView(mContentView)
        }
        mContentView = bindContentView(inflater, container, savedInstanceState)
        return mContentView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindStatusBar(view)
        bindToolbar(view)
        bindView(view)
        bindData()
        bindEvent()
    }

    protected open fun bindContentView(inflater: LayoutInflater?, parent: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(bindContentViewId(), parent, false)
    }

    abstract fun bindContentViewId(): Int

    protected open fun bindStatusBar(view: View?) {

    }

    protected open fun bindToolbar(view: View?) {

    }

    abstract fun bindView(view: View?)

    abstract fun bindData()

    abstract fun bindEvent()


    override fun showToast(message: String, length: Int) {
        if (toast == null) {
            toast = Toast.makeText(mContext, message, length)
            toast?.show()
        } else {
            toast?.setText(message)
            toast?.show()
        }
    }

    override fun <T : Activity> simpleTo(clazz: Class<T>) {
        routerTo(clazz)
    }

    fun <V : View> findViewById(id: Int): V {
        return mContentView!!.findViewById(id)
    }

    override fun showLoading(message: String, cancelable: Boolean): AlertDialog {
        return DialogUtil.showLoadingDialog(mContext!!, message, cancelable)
    }

    override fun hideDialog(dialog: Dialog?) {
        DialogUtil.hide(dialog)
    }
}