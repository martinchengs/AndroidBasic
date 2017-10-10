/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.martin.basic.library.widget.navigation

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.support.annotation.RestrictTo
import android.support.v4.util.Pools
import android.support.v7.view.SupportMenuInflater
import android.support.v7.view.menu.MenuBuilder
import android.support.v7.widget.TintTypedArray
import android.util.AttributeSet
import android.view.*
import android.widget.LinearLayout
import com.martin.basic.library.R
import com.martin.basic.library.log.LogX

/**
 *
 */
@SuppressLint("RestrictedApi")
class UIBottomNavigationBar : LinearLayout {
    private val mMenu: MenuBuilder
    private var textSize: Int = 0
    private var textColor: Int
    private var singleIconSize: Int = 0
    private var dotStyle: Int
    private var dotBackground: Drawable?
    private var dotWidth: Int
    private var dotHeight: Int
    private var navigationDotVerticalBias: Float
    private var navigationDotHorizontalBias: Float
    private var chainStyle: Int = 0
    private val lp: LinearLayout.LayoutParams = LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1.0f)
    private val mMenuInflater: MenuInflater by lazy {
        SupportMenuInflater(context)
    }
    private val onChildViewClick: OnClickListener
    private var onItemClickListener: ((index: Int) -> Unit)? = null
    private var mButtons: Array<UIBottomNavigationItemView?>? = null


    private val mPools = Pools.SynchronizedPool<UIBottomNavigationItemView>(5)

    constructor(context: Context?) : this(context, null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)


    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        //set ViewGroup attrs
        orientation = HORIZONTAL
        gravity = Gravity.CENTER_VERTICAL

        mMenu = MenuBuilder(context)
        val a = context!!.obtainStyledAttributes(attrs, R.styleable.UIBottomNavigationBar)

        textSize = a.getDimensionPixelSize(R.styleable.UIBottomNavigationBar_navigationTextSize, 14)
        textColor = a.getColor(R.styleable.UIBottomNavigationBar_navigationTextColor, Color.BLACK)
        if (a.hasValue(R.styleable.UIBottomNavigationBar_singleNavigationIconSize)) {
            singleIconSize = a.getDimensionPixelSize(R.styleable.UIBottomNavigationBar_singleNavigationIconSize, 0)
        }
        dotStyle = a.getInt(R.styleable.UIBottomNavigationBar_navigationDotStyle, 0)
        chainStyle = a.getInt(R.styleable.UIBottomNavigationBar_navigationVerticalChainStyle, 0)
        dotWidth = a.getDimensionPixelSize(R.styleable.UIBottomNavigationBar_navigationDotWidth, 92)
        dotHeight = a.getDimensionPixelSize(R.styleable.UIBottomNavigationBar_navigationDotHeight, 32)
        dotBackground = a.getDrawable(R.styleable.UIBottomNavigationBar_navigationDotBackground)
        navigationDotVerticalBias = a.getFloat(R.styleable.UIBottomNavigationBar_navigationDotVerticalBias, 0f)
        navigationDotHorizontalBias = a.getFloat(R.styleable.UIBottomNavigationBar_navigationDotHorizontalBias, 0.5f)

        if (a.hasValue(R.styleable.UIBottomNavigationBar_navigationMenu)) {
            inflateNavigationMenu(a.getResourceId(R.styleable.UIBottomNavigationBar_navigationMenu, 0))
        } else {
            throw IllegalArgumentException("the attribute app:navigationMenu must be set") as Throwable
        }
        a.recycle()

        onChildViewClick = View.OnClickListener {
            it as UIBottomNavigationItemView
            val index = it.getItemIndex()
            onItemClickListener?.invoke(index)
        }
    }

    private fun inflateNavigationMenu(menuResId: Int) {
        mMenuInflater.inflate(menuResId, mMenu)
        buildMenuItems()
    }


    private fun getNavItem(): UIBottomNavigationItemView {
        var childView = mPools.acquire()
        if (childView == null) {
            childView = UIBottomNavigationItemView(context)
        }
        return childView
    }


    fun buildMenuItems() {
        removeAllViews()
        mButtons?.forEach { mPools.release(it) }
        mButtons = arrayOfNulls<UIBottomNavigationItemView>(mMenu.size())
        for (i in 0 until mMenu.size()) {
            val childView = getNavItem()
            mButtons!![i] = childView
            childView.setSingleImageSize(i, mMenu.size(), singleIconSize)
            childView.setItemTextSize(textSize)
            childView.setItemTextColor(textColor)
            childView.setItemChainStyle(chainStyle)
            childView.setVerticalGuidelinePercent(navigationDotVerticalBias)
            childView.setHorizontalGuidelinePercent(navigationDotHorizontalBias)
            childView.setDotAppearance(dotStyle, dotWidth, dotHeight, dotBackground)
            childView.initialize(mMenu.getItem(i))
            childView.setItemIndex(i)
            childView.layoutParams = lp
            childView.setOnClickListener(onChildViewClick)
            addView(childView)
        }
    }

    fun setOnItemClickListener(listener: (index:Int)->Unit) {
        this.onItemClickListener = listener
    }
}