package com.martin.basic.library.app

import com.martin.basic.library.util.EventBusUtil
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
class ViewModelDelegate : IViewModel, UseEventBus {
    private val mDisposables: CompositeDisposable by lazy {
        CompositeDisposable()
    }
    private val mDisposablesMap: HashMap<String, Disposable> by lazy {
        HashMap<String, Disposable>()
    }

    override fun onAttach() {
        EventBusUtil.register(this)
    }

    override fun onDeAttach() {
        EventBusUtil.unregister(this)
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
}