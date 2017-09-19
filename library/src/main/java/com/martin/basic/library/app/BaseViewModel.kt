package com.martin.basic.library.app

/**
 * Created by Martin on 2017/9/10.
 * Hello World
 */
open class BaseViewModel<V : IView>(var view: V?, val delegate: IViewModel = ViewModelDelegate())
    : IViewModel by delegate, UseEventBus