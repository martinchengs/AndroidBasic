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
    var mToolbar: ToolbarWrapper? = null
    var mContext: Context? = null
    var mContentView: View? = null
    var mActivity: AppMobileActivity? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        this.mContext = context
        if (context is AppMobileActivity) {
            mActivity = context
            mToolbar = mActivity?.getToolbar()
        }
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
        afterViewCreated()
        bindStatusBar(view)
        bindToolbar(view)
        bindView(view)
        bindData()
        bindEvent()
    }

    open fun afterViewCreated() {

    }

    protected open fun bindContentView(inflater: LayoutInflater?, parent: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(bindContentViewId(), parent, false)
    }

    protected open fun bindStatusBar(view: View?) {

    }

    protected open fun bindToolbar(view: View?) {

    }

    abstract fun bindContentViewId(): Int

    abstract fun bindView(view: View?)

    abstract fun bindData()

    abstract fun bindEvent()


    override fun showToast(message: String, length: Int) {
        mActivity?.showToast(message, length)
    }

    override fun <T : Activity> simpleTo(clazz: Class<T>) {
        routerTo(clazz)
    }

    fun <V : View> findViewById(id: Int): V {
        return mContentView!!.findViewById(id)
    }

    override fun showLoading(message: String, cancelable: Boolean): AlertDialog {
        return mActivity?.showLoading(message, cancelable)!!
    }

    override fun hideDialog(dialog: Dialog?) {
        mActivity?.hideDialog(dialog)
    }
}