package com.martin.basic.library.util

import com.martin.basic.library.app.BaseViewModel
import com.martin.basic.library.app.IViewMode
import org.greenrobot.eventbus.EventBus

/**
 * Created by Martin on 2017/8/14.
 * Hello World
 */
object EventBusUtil {

    fun isRegistered(any: Any?): Boolean = EventBus.getDefault().isRegistered(any)

    fun <T : IViewMode> register(viewModel: T) {
        if (isRegistered(viewModel).not() && viewModel.useEventBus()) {
            EventBus.getDefault().register(viewModel)
        }
    }

    fun <T : IViewMode> unregister(viewModel: T) {
        if (isRegistered(viewModel) && viewModel.useEventBus()) {
            EventBus.getDefault().unregister(viewModel)
        }
    }
}