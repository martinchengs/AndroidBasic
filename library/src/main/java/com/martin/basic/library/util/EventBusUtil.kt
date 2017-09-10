package com.martin.basic.library.util

import com.martin.basic.library.app.UseEventBus
import org.greenrobot.eventbus.EventBus

/**
 * Created by Martin on 2017/8/14.
 * Hello World
 */
object EventBusUtil {

    fun isRegistered(any: Any?): Boolean = EventBus.getDefault().isRegistered(any)

    fun <T : UseEventBus> register(useEventBus: T) {
        if (isRegistered(useEventBus).not() && useEventBus.useEventBus()) {
            EventBus.getDefault().register(useEventBus)
        }
    }

    fun <T : UseEventBus> unregister(useEventBus: T) {
        if (isRegistered(useEventBus) && useEventBus.useEventBus()) {
            EventBus.getDefault().unregister(useEventBus)
        }
    }
}