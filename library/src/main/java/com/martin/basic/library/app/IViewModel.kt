package com.martin.basic.library.app

import io.reactivex.disposables.Disposable

/**
 * Created by Martin on 2017/8/13.
 * Hello World
 */
interface IViewModel {
    fun onAttach()
    fun onDeAttach()
    fun addDisposable(tag: String = "Default", disposable: Disposable)
    fun removeDisposable(tag: String="Default")
    fun clearDisposable()
}