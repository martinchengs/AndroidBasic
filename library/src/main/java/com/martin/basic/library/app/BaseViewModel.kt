package com.martin.basic.library.app

import android.os.Bundle
import com.martin.basic.library.util.EventBusUtil
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.greenrobot.eventbus.EventBus

/**
 * Created by Martin on 2017/8/13.
 * Hello World
 */
open class BaseViewModel<V : IView> constructor(var view: V?) : IViewMode {
    private val mDisposables: CompositeDisposable by lazy {
        CompositeDisposable()
    }
    private val mDisposablesMap: HashMap<String, Disposable> by lazy {
        HashMap<String, Disposable>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        EventBusUtil.register(this)
    }


    override fun addDisposable(tag: String?, disposable: Disposable) {
        mDisposables.add(disposable)
        tag?.let { mDisposablesMap.put(tag, disposable) }
    }

    override fun removeDisposable(tag: String) {
        mDisposablesMap[tag]?.dispose()
        val disposable = mDisposablesMap[tag]
        disposable?.let {
            mDisposables.remove(disposable)
            mDisposablesMap.remove(tag)
        }
    }

    override fun onDestroy() {
        EventBusUtil.unregister(this)
        view = null
    }
}