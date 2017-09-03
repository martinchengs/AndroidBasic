package com.martin.basic.ui.register

import com.martin.basic.BR
import com.martin.basic.R
import com.martin.basic.databinding.ActivityRegisterBinding
import com.martin.basic.library.app.BaseActivity

/**
 * Created by Martin on 2017/8/28.
 * Hello World
 */
class RegisterActivity : BaseActivity<ActivityRegisterBinding, RegisterViewModel>(), RegisterView {
    override fun getVmId(): Int = BR.vm

    override fun bindEvent() {
    }

    override fun bindData() {
    }

    override fun bindViewModel(): RegisterViewModel = RegisterViewModel(this)

    override fun bindLayoutId(): Int = R.layout.activity_register
}