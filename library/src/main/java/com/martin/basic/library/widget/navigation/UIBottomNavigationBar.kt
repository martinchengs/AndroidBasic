/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.martin.basic.library.widget.navigation

import android.content.Context
import android.support.constraint.Guideline
import android.support.v7.view.SupportMenuInflater
import android.support.v7.view.menu.MenuBuilder
import android.support.v7.widget.TintTypedArray
import android.util.AttributeSet
import android.util.SparseArray
import android.view.Gravity
import android.view.MenuInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.martin.basic.library.R

/**
 *
 */
class UIBottomNavigationBar : LinearLayout {

    private val mNavViews: SparseArray<View> by lazy {
        SparseArray<View>()
    }
    private val mMenu: MenuBuilder
    private val mMenuInflater: MenuInflater by lazy {
        SupportMenuInflater(context)
    }

    constructor(context: Context?) : this(context, null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {

        //set ViewGroup attrs

        orientation = HORIZONTAL
        gravity = Gravity.CENTER_VERTICAL

        mMenu = MenuBuilder(context)
        val a = TintTypedArray.obtainStyledAttributes(context, attrs, R.styleable.UIBottomNavigationBar)
        if (a.hasValue(R.styleable.UIBottomNavigationBar_navigationMenu)) {
            inflateNavigationMenu(a.getResourceId(R.styleable.UIBottomNavigationBar_navigationMenu, 0))
        } else {
            throw IllegalArgumentException("the attribute app:navigationMenu must be set")
        }
        a.recycle()


    }

    private fun inflateNavigationMenu(menuResId: Int) {
        removeAllViews()
        mNavViews.clear()
        mMenuInflater.inflate(menuResId, mMenu)
        for (i in 0 until mMenu.size()) {
            val item = mMenu.getItem(i)
            val title = item.title
            val icon = item.icon
            if (title == null || icon == null) {
                throw IllegalArgumentException("the attribute android:title and android:icon must be set")
            }
            val view = View.inflate(context, R.layout.layout_ui_bottom_navigation_bar_cell, null)
            val imageView = view.findViewById<ImageView>(R.id.image)
            val textView = view.findViewById<TextView>(R.id.text)
            val dotView = view.findViewById<TextView>(R.id.dotView)
            val guidelineH = view.findViewById<Guideline>(R.id.guideline_horizontal)
            val guidelineV = view.findViewById<Guideline>(R.id.guideline_vertical)
            val lp = LayoutParams(0, LayoutParams.WRAP_CONTENT)

            lp.weight = 1.0f
            lp.gravity = Gravity.CENTER
            view.layoutParams = lp
            imageView.setImageDrawable(icon)
            textView.text = title
            addView(view)
        }
    }
}