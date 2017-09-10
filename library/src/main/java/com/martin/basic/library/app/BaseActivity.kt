package com.martin.basic.library.app

import android.app.Activity
import android.app.Dialog
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.annotation.LayoutRes
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.martin.basic.library.R
import com.martin.basic.library.ex.routerTo
import com.martin.basic.library.util.DialogUtil

/**
 * Created by Martin on 2017/8/13.
 * Hello World
 */
abstract class BaseActivity<B : ViewDataBinding, VM : IViewModel> : AppCompatActivity(), IView {

    lateinit var binding: B
    lateinit var vm: VM
    private var toast: Toast? = null
    lateinit var toolbar: ToolbarWrapper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        beforeViewSet()
        binding = DataBindingUtil.setContentView<B>(this, bindLayoutId())
        vm = bindViewModel()
        binding.setVariable(getVmId(), vm)
        vm.onAttach()
        bindToolbar()
        bindView()
        bindData()
        bindEvent()
    }

    open fun beforeViewSet() {

    }

    abstract fun getVmId(): Int

    private fun bindToolbar() {
        toolbar = ToolbarWrapper(this, findViewById(R.id.toolbar))
    }

    @CallSuper
    open fun bindView() {

    }


    abstract fun bindEvent()

    abstract fun bindData()

    abstract fun bindViewModel(): VM

    @LayoutRes
    abstract fun bindLayoutId(): Int

    override fun showToast(message: String, length: Int) {
        if (toast == null) {
            toast = Toast.makeText(this, message, length)
            toast?.show()
        } else {
            toast?.setText(message)
            toast?.show()
        }
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

    override fun onDestroy() {
        vm.onDeAttach()
        super.onDestroy()
    }
}