package com.martin.basic.library.app

import android.support.annotation.DrawableRes
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.TextView
import com.martin.basic.library.R

/**
 * Created by Martin on 2017/9/3.
 * Hello World
 */
class ToolbarWrapper(val activity: AppCompatActivity, val toolbar: Toolbar?) {

    init {
        activity.setSupportActionBar(toolbar)
    }

    fun setTitle(title: String): ToolbarWrapper {
        val titleView = toolbar?.findViewById<TextView>(R.id.toolbar_title)
        if (titleView == null) {
            getSupportActionBar()?.title = title
        } else {
            getSupportActionBar()?.setDisplayShowTitleEnabled(false)
            titleView.text = title
        }
        return this
    }

    fun setContentView(view: View): ToolbarWrapper {
        getSupportActionBar()?.apply {
            setContentView(view)
            setDisplayHomeAsUpEnabled(false)
            setDisplayShowTitleEnabled(false)
            setDisplayShowCustomEnabled(true)
        }
        return this
    }


    fun setBackIcon(@DrawableRes backRes: Int): ToolbarWrapper {
        if (backRes > 0) {
            toolbar?.setNavigationIcon(backRes)
            toolbar?.setNavigationOnClickListener { activity.onBackPressed() }
        } else {
            getSupportActionBar()?.setDisplayHomeAsUpEnabled(false)
        }
        return this
    }

    fun setBackClickExcutor(listener: View.OnClickListener): ToolbarWrapper {
        toolbar?.setNavigationOnClickListener(listener)
        return this
    }

    fun getSupportActionBar() = activity.supportActionBar
}