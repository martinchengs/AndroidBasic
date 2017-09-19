package com.martin.basic.library.app

import com.martin.basic.library.util.EventBusUtil
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class ViewModelDelegate : IViewModel, UseEventBus {


    private val mDisposables: CompositeDisposable by lazy {
        CompositeDisposable()
    }
    private val mDisposablesMap: MutableMap<String, MutableList<Disposable>>  by lazy {
        HashMap<String, MutableList<Disposable>>()
    }

    override fun onAttach() {
        EventBusUtil.register(this)
    }

    override fun onDeAttach() {
        EventBusUtil.unregister(this)
    }

    override fun addDisposable(tag: String, disposable: Disposable) {
        mDisposables.add(disposable)
        val disposes = mDisposablesMap.getOrDefault(tag, mutableListOf())
        disposes.add(disposable)
    }

    override fun removeDisposable(tag: String) {
        val list = mDisposablesMap[tag]
        val listIterator = list?.listIterator()
        if (listIterator != null) {
            while (listIterator.hasNext()) {
                val next = listIterator.next()
                mDisposables.remove(next)
            }
        }
        mDisposablesMap.remove(tag)
    }

    override fun clearDisposable() {
        mDisposablesMap.clear()
        mDisposables.clear()
    }
}