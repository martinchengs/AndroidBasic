package com.martin.basic.ui.login

import android.databinding.BindingConversion
import android.databinding.ObservableField
import android.view.View
import com.martin.basic.library.app.BaseViewModel
import com.martin.basic.ui.router.LoginView

/**
 * Created by Martin on 2017/8/21.
 * Hello World
 */
class LoginViewModel(view: LoginView) : BaseViewModel<LoginView>(view) {
    val accountField = ObservableField<String>()
    val passwordField = ObservableField<String>()

    init {
        accountField.set("martin")
    }

    fun onEnterClick(view: View) {
        this.view?.showToast("${accountField.get()}    ${passwordField.get()}")
    }

    fun onAboutClick(view:View) {
        this.view?.enterAboutActivity()
    }
}