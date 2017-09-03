package com.martin.basic.ui.login

import com.martin.basic.library.app.DataSource
import com.martin.basic.library.database.User
import io.reactivex.Flowable

/**
 * Created by Martin on 2017/8/29.
 * Hello World
 */
class LoginDS : DataSource<User>() {
    override fun fetch(map: MutableMap<String, Any?>): Flowable<User> {
        return Flowable.empty()
    }
}