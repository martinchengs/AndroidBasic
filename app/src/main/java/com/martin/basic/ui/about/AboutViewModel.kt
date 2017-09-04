package com.martin.basic.ui.about

import com.martin.basic.BuildConfig
import com.martin.basic.library.app.BaseViewModel

/**
 * Created by Martin on 2017/9/4.
 * Hello World
 */
class AboutViewModel(view: AboutView) : BaseViewModel<AboutView>(view) {
    val appName: String = "图趣"
    val appVersion = "版本：${BuildConfig.VERSION_NAME}"
    val qq = "QQ：1206011655"
    val email = "邮箱：martin@qq.com"

}