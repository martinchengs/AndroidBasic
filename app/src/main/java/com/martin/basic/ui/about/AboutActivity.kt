package com.martin.basic.ui.about

import com.martin.basic.BR
import com.martin.basic.R
import com.martin.basic.databinding.ActivityAboutBinding
import com.martin.basic.library.app.BaseActivity

/**
 * Created by Martin on 2017/9/4.
 * Hello World
 */
class AboutActivity : BaseActivity<ActivityAboutBinding, AboutViewModel>(), AboutView {
    override fun getVmId() = BR.vm

    override fun bindEvent() {
    }

    override fun bindView() {
        super.bindView()
        toolbar.setTitle("关于")
    }

    override fun bindData() {
    }

    override fun bindViewModel() = AboutViewModel(this)

    override fun bindLayoutId() = R.layout.activity_about
}