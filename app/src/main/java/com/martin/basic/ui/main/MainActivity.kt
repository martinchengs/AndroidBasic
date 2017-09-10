package com.martin.basic.ui.main;

import android.content.Context
import android.media.MediaPlayer
import android.support.v4.view.ViewPager
import android.util.Log
import android.widget.ImageView
import com.martin.basic.BR
import com.martin.basic.R
import com.martin.basic.databinding.ActivityMainBinding
import com.martin.basic.library.app.BaseActivity
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import com.youth.banner.loader.ImageLoaderInterface

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(), MainView {
    override fun getVmId(): Int {
        return BR.vm
    }

    override fun bindView() {
        super.bindView()
        binding.mainViewPager.adapter = MainAdapter(supportFragmentManager)

    }

    override fun bindEvent() {
    }

    override fun bindData() {
        binding.vm = vm
    }

    override fun bindViewModel(): MainViewModel {
        return MainViewModel(this)
    }

    override fun bindLayoutId(): Int {
        return R.layout.activity_main
    }
}
