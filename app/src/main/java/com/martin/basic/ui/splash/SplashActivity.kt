package com.martin.basic.ui.splash

import android.os.Handler
import android.support.design.widget.BottomNavigationView
import android.support.v7.view.menu.MenuBuilder
import com.martin.basic.BR
import com.martin.basic.R
import com.martin.basic.databinding.ActivitySplashBinding
import com.martin.basic.library.app.BaseActivity
import com.martin.basic.library.ex.routerTo
import com.martin.basic.ui.router.RouterActivity

/**
 * Created by Martin on 2017/8/20.
 * Hello World
 */
class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>(), SplashView {

    override fun getVmId(): Int {
        return BR.vm
    }

    override fun bindEvent() {
        val menu:MenuBuilder
    }

    override fun bindData() {
        Handler().postDelayed({ routerTo(RouterActivity::class.java) }, 3000)
    }

    override fun bindViewModel(): SplashViewModel {
        return SplashViewModel(this)
    }

    override fun bindLayoutId(): Int = R.layout.activity_splash
}