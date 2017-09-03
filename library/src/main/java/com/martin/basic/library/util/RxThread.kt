package com.martin.common.util

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by Martin on 2017/8/7.
 * Hello World
 */
object RxThread {
    fun <T> run(background: () -> T, mainResult: (T) -> Unit): Disposable {
        return Observable.create<T> {
            it.onNext(background())
            it.onComplete()
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    mainResult(it)
                }, {})
    }

    fun run(background: () -> Unit): Disposable {
        return Observable.create<Any> {
            background()
            it.onComplete()
        }.subscribeOn(Schedulers.io()).subscribe({}, {})
    }
}