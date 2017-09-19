package com.martin.basic.library.ex

import android.app.Activity
import android.support.annotation.IdRes
import com.martin.basic.library.R
import com.martin.basic.library.util.PageRouter

/**
 * Created by Martin on 2017/8/6.
 * Hello World
 */
fun Activity.routerTo(clazz: Class<out Activity>) {
    PageRouter.toActivity(this, clazz)
}

fun Activity.withResult(clazz: Class<out Activity>, requestCode: Int) {
    PageRouter.toActivityForResult(this, clazz, requestCode)
}

fun Activity.fragment(fragment: android.app.Fragment, @IdRes contentId: Int = R.id.fragment_content, tag: String = fragment.tag) {
    PageRouter.addFragment(this, fragment, contentId, tag)
}

fun Activity.fragment(fragment: android.support.v4.app.Fragment, @IdRes contentId: Int = R.id.fragment_content, tag: String = fragment.tag) {
    PageRouter.addFragment(this, fragment, contentId, tag)
}

