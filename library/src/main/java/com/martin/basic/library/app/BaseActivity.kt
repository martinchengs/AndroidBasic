package com.martin.basic.library.app

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

/**
 * Created by Martin on 2017/8/13.
 * Hello World
 */
abstract class BaseActivity<B : ViewDataBinding, VM : IViewMode> : AppCompatActivity(), IView {

    public lateinit var binding: B
    public lateinit var vm: VM
        public get
    private var toast: Toast? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<B>(this, bindLayoutId())
        vm = bindViewModel()
        vm.onCreate(savedInstanceState)
        bindView()
        bindData()
        bindEvent()
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
            toast!!.show()
        } else {
            toast!!.setText(message)
            toast!!.show()
        }
    }

    override fun showLoading(message: String, cancelable: Boolean): ILoadingView {
        return null!!
    }

    override fun onDestroy() {
        vm.onDestroy()
        super.onDestroy()
    }
}