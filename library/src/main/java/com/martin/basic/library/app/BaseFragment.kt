package com.martin.basic.library.app

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
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
 * Created by Martin on 2017/8/20.
 * Hello World
 */
abstract class BaseFragment<B : ViewDataBinding, VM : IViewModel> : Fragment(), IView {
    lateinit var binding: B
    lateinit var vm: VM
    var rootView: View? = null
    private var toast: Toast? = null
    lateinit var toolbar: ToolbarWrapper
    lateinit var mContext: Context
    private var parentView: IView? = null


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        this.mContext = context!!
        if (mContext is IView) {
            parentView = mContext as IView
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate<B>(inflater, bindLayoutId(), container, false)
        if (rootView != null && container != null) {
            container.removeView(rootView)
        }
        rootView = binding.root
        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm = bindViewModel()
        binding.setVariable(getVmId(), vm)
        vm.onAttach()
        bindView()
        bindData()
        bindEvent()
    }

    abstract fun getVmId(): Int
    abstract fun bindLayoutId(): Int
    abstract fun bindViewModel(): VM
    abstract fun bindView()
    abstract fun bindData()
    abstract fun bindEvent()

    override fun showToast(message: String, length: Int) {
        parentView?.showToast(message, length)
    }

    override fun <T : Activity> simpleTo(clazz: Class<T>) {
        routerTo(clazz)
    }

    override fun showLoading(message: String, cancelable: Boolean): AlertDialog {
        return parentView?.showLoading(message, cancelable) ?: DialogUtil.showLoadingDialog(mContext, message, cancelable)
    }

    override fun hideDialog(dialog: Dialog?) {
        parentView?.hideDialog(dialog)
    }

    override fun onDestroy() {
        vm.onDeAttach()
        super.onDestroy()
    }


}