package com.martin.basic.ui.login

import android.databinding.Observable
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import com.martin.basic.BR
import com.martin.basic.R
import com.martin.basic.databinding.ActivityLoginBinding
import com.martin.basic.library.app.BaseActivity
import com.martin.basic.library.ex.routerTo
import com.martin.basic.ui.about.AboutActivity
import com.martin.basic.ui.router.LoginView

/**
 * Created by Martin on 2017/8/21.
 * Hello World
 */
class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>(), LoginView {
    override fun getVmId(): Int = BR.vm

    override fun bindEvent() {

    }

    override fun bindData() {
    }

    override fun bindView() {
        super.bindView()
        toolbar.setTitle("登录").setBackIcon(R.drawable.btn_back_normal)
    }

    override fun bindViewModel(): LoginViewModel = LoginViewModel(this)

    override fun bindLayoutId(): Int = R.layout.activity_login
}