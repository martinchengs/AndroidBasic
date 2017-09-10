package com.martin.basic.ui.router

import android.view.View
import com.martin.basic.library.app.BaseViewModel

/**
 * Created by Martin on 2017/8/20.
 * Hello World
 */
class RouterViewModel(view: RouterView) : BaseViewModel<RouterView>(view) {


    fun onLoginClick(view: View) {
        this.view?.openLoginActivity()
    }

    fun onLoggingClick(view: View) {
        this.view?.showToast("注册点击")
    }
}