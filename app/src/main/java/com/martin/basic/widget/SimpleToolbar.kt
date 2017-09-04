package com.martin.basic.widget

import android.content.Context
import android.content.res.TypedArray
import android.graphics.drawable.ColorDrawable
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.ViewGroup
import android.widget.TextView
import com.martin.basic.R

/**
 * Created by Martin on 2017/9/4.
 * Hello World
 */
class SimpleToolbar(context: Context, attrs: AttributeSet) : Toolbar(context, attrs) {
    private var pageTitle: String?
    private var showNavigationIcon: Boolean

    private var titleView: TextView

    init {
        val a = context.obtainStyledAttributes(attrs, R.styleable.SimpleToolbar)
        pageTitle = a.getString(R.styleable.SimpleToolbar_pageTitle)
        showNavigationIcon = a.getBoolean(R.styleable.SimpleToolbar_showNavigationIcon, true)
        a.recycle()

        //添加一个标题视图
        titleView = TextView(context)
        titleView.id = R.id.toolbar_title
        titleView.setSingleLine(true)
        val layoutParams = LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        layoutParams.gravity = Gravity.CENTER
        titleView.layoutParams = layoutParams
        titleView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)
        titleView.setTextColor(ResourcesCompat.getColor(resources, R.color.second_color, null))
        titleView.text = pageTitle
        addView(titleView)

        navigationIcon = if (showNavigationIcon) ContextCompat.getDrawable(context, R.drawable.btn_back_normal) else null

        setContentInsetsAbsolute(0, 0)
        setContentInsetsRelative(0, 0)

    }

    override fun setTitle(title: CharSequence?) {
        if (titleView.text != title)
            titleView.text = title
    }

    override fun setNavigationIcon(resId: Int) {
        if (resId != R.drawable.btn_back_normal) {
            super.setNavigationIcon(resId)
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        if (context is AppCompatActivity) {
            if (showNavigationIcon) {
                (context as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
            }
            (context as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(false)
        }

        setNavigationOnClickListener {
            if (context is AppCompatActivity) {
                (context as AppCompatActivity).finish()
            }
        }
    }

    override fun onDetachedFromWindow() {
        setNavigationOnClickListener(null)
        super.onDetachedFromWindow()
    }
}