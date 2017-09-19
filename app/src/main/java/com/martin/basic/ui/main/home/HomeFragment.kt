package com.martin.basic.ui.main.home

import android.app.ActivityManager
import android.content.Context
import android.graphics.Camera
import com.martin.basic.BR
import com.martin.basic.R
import com.martin.basic.databinding.FragmentHomeBinding
import com.martin.basic.library.app.AppContext
import com.martin.basic.library.app.BaseFragment
import com.martin.basic.library.app.ResourceType

/**
 * Created by Martin on 2017/9/10.
 * Hello World
 */
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(), HomeView {
    override fun getVmId(): Int = BR.vm

    override fun bindLayoutId(): Int = R.layout.fragment_home

    override fun bindViewModel(): HomeViewModel = HomeViewModel(this)

    override fun bindView() {
    }

    override fun bindData() {
    }

    override fun bindEvent() {
    }
}