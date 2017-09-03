package com.martin.basic.library.app

import io.reactivex.Flowable

/**
 * Created by Martin on 2017/8/29.
 * Hello World
 */
abstract class DataSource<T> {

    abstract fun fetch(map: MutableMap<String, Any?>): Flowable<T>


}