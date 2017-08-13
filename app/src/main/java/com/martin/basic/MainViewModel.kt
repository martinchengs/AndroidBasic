package com.martin.basic

import com.martin.basic.library.app.BaseViewModel

/**
 * Created by Martin on 2017/8/13.
 * Hello World
 */
class MainViewModel(view: MainView) : BaseViewModel<MainView>(view) {
    fun requestInfo() {
        view?.hello()
    }
}