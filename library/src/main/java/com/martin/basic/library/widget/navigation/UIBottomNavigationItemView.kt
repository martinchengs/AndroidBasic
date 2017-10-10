/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.martin.basic.library.widget.navigation

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.graphics.drawable.shapes.Shape
import android.support.annotation.IntRange
import android.support.constraint.ConstraintLayout
import android.support.constraint.Guideline
import android.support.v4.content.ContextCompat
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import com.martin.basic.library.R

/**
 * by Martin
 * at 2017/10/9 22:24
 */
internal class UIBottomNavigationItemView(context: Context?) : ConstraintLayout(context) {

    private var imageView: ImageView
    private var textView: TextView
    private var dotView: TextView
    private var guidelineH: Guideline
    private var guidelineV: Guideline
    private var itemIndex: Int = 0

    init {
        LayoutInflater.from(context).inflate(R.layout.layout_ui_bottom_navigation_bar_cell, this)
        imageView = findViewById<ImageView>(R.id.image)
        textView = findViewById<TextView>(R.id.text)
        dotView = findViewById<TextView>(R.id.dotView)
        guidelineH = findViewById<Guideline>(R.id.guideline_horizontal)
        guidelineV = findViewById<Guideline>(R.id.guideline_vertical)
    }

    fun initialize(item: MenuItem): Unit {
        val icon = item.icon
        imageView.setImageDrawable(icon)
        textView.text = item.title
    }

    fun setSingleImageSize(index: Int, size: Int, singleIconSize: Int) {
        if (singleIconSize == 0) return
        if (size % 2 == 0) return
        if (size <= 2) return
        val c = size / 2
        if (c != index) return
        val lp = imageView.layoutParams as ConstraintLayout.LayoutParams
        lp.width = singleIconSize
        lp.height = singleIconSize
        imageView.layoutParams = lp
        removeView(textView)
    }

    fun setItemTextSize(textSize: Int) {
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize.toFloat())
    }

    fun setItemIndex(index: Int) {
        this.itemIndex = index
    }

    fun getItemIndex() = itemIndex


    fun setItemTextColor(textColor: Int) {
        textView.setTextColor(textColor)
    }

    fun setItemChainStyle(style: Int) {
        val lp = imageView.layoutParams as ConstraintLayout.LayoutParams
        lp.verticalChainStyle = style
        imageView.layoutParams = lp
    }

    fun setVerticalGuidelinePercent(navigationDotVerticalBias: Float) {
        val lp = guidelineV.layoutParams as ConstraintLayout.LayoutParams
        lp.guidePercent = navigationDotVerticalBias
        guidelineV.layoutParams = lp
    }

    fun setHorizontalGuidelinePercent(navigationDotHorizontalBias: Float) {
        val lp = guidelineH.layoutParams as ConstraintLayout.LayoutParams
        lp.guidePercent = navigationDotHorizontalBias
        guidelineH.layoutParams = lp
    }

    fun setDotAppearance(dotStyle: Int, dotWidth: Int, dotHeight: Int, dotBackground: Drawable?) {
        val lp = dotView.layoutParams as ConstraintLayout.LayoutParams
        when {
            dotBackground != null -> {
                dotView.background = dotBackground
                lp.width = dotWidth
                lp.height = dotHeight
            }
            dotStyle == 0 -> {
                val max = Math.max(dotWidth, dotHeight)
                lp.width = max
                lp.height = max
                dotView.background = ContextCompat.getDrawable(context, R.drawable.ui_circle_red_dot)
            }

            dotStyle == 1 -> {
                lp.width = dotWidth
                lp.height = dotHeight
                val d = ShapeDrawable()
                d.paint.color = Color.RED
                d.setBounds(0, 0, dotWidth, dotHeight)
                val radius = dotHeight / 2.0f
                val radiusArrays = floatArrayOf(radius, radius, radius, radius, radius, radius, radius, radius)
                val s = RoundRectShape(radiusArrays, null, radiusArrays)
                d.shape = s
                dotView.background = d
            }
        }
        dotView.layoutParams = lp
    }
}