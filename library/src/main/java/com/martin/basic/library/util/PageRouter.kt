package com.martin.common.util

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Fragment
import android.app.FragmentTransaction
import android.content.Context
import android.content.Intent
import android.support.annotation.IdRes
import android.support.v4.app.FragmentActivity
import com.martin.basic.library.R

/**
 * Created by Martin on 2017/8/6.
 * Hello World
 */
object PageRouter {

    fun toActivity(page: Any, clazz: Class<out Activity>) {
        when (page) {
             is Activity -> {
                val intent = Intent(page, clazz)
                page.startActivity(intent)
            }
             is android.app.Fragment -> {
                page.activity?.let {
                    val intent = Intent(it, clazz)
                    it.startActivity(intent)
                }
            }
             is android.support.v4.app.Fragment -> {
                page.activity?.let {
                    val intent = Intent(it, clazz)
                    it.startActivity(intent)
                }
            }
             is Context -> {
                val intent = Intent(page, clazz)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                page.startActivity(intent)
            }
            else -> {
                throw IllegalArgumentException("page type is not support")
            }
        }
    }

    fun toActivityForResult(page: Any, clazz: Class<out Activity>, requestCode: Int) {
        when (page) {
            page is Activity -> {
                val intent = Intent(page as Activity, clazz)
                page.startActivityForResult(intent, requestCode)
            }
            page is android.app.Fragment -> {
                (page as android.app.Fragment).activity?.let {
                    val intent = Intent(it, clazz)
                    it.startActivityForResult(intent, requestCode)
                }
            }
            page is android.support.v4.app.Fragment -> {
                (page as android.support.v4.app.Fragment).activity?.let {
                    val intent = Intent(it, clazz)
                    it.startActivityForResult(intent, requestCode)
                }
            }
            else -> {
                throw IllegalArgumentException("page type is not support")
            }
        }
    }

    fun addFragment(page: Any, fragment: android.app.Fragment, @IdRes contentId: Int = R.id.fragment_content, tag: String = fragment.tag) {
        when (page) {
            page is FragmentActivity -> {
                val fm = (page as FragmentActivity).fragmentManager
                fm.beginTransaction().add(contentId, fragment, tag).commit()
            }

            page is android.app.Fragment -> {
                val fm = (page as android.app.Fragment).childFragmentManager
                fm.beginTransaction().add(contentId, fragment, tag).commit()
            }
            else -> {
                throw IllegalArgumentException("page type is not support")
            }
        }
    }

    fun addFragment(page: Any, fragment: android.support.v4.app.Fragment, @IdRes contentId: Int = R.id.fragment_content, tag: String = fragment.tag) {
        when (page) {
            page is FragmentActivity -> {
                val fm = (page as FragmentActivity).supportFragmentManager
                fm.beginTransaction().add(contentId, fragment, tag).commit()
            }

            page is android.support.v4.app.Fragment -> {
                val fm = (page as android.support.v4.app.Fragment).childFragmentManager
                fm.beginTransaction().add(contentId, fragment, tag).commit()
            }
            else -> {
                throw IllegalArgumentException("page type is not support")
            }
        }
    }
}