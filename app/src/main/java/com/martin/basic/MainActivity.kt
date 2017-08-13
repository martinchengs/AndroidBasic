package com.martin.basic;

import com.martin.basic.databinding.ActivityMainBinding
import com.martin.basic.library.app.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(), MainView {
    override fun bindEvent() {
    }

    override fun bindData() {
        vm.requestInfo()
    }

    override fun hello() {
        
    }

    override fun bindViewModel(): MainViewModel {
        return MainViewModel(this)
    }

    override fun bindLayoutId(): Int {
        return R.layout.activity_main
    }


}
