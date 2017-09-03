package com.martin.basic.widget

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.StateListDrawable
import android.graphics.drawable.shapes.Shape
import android.os.Build
import android.util.AttributeSet
import android.widget.Button
import com.martin.basic.R

/**
 * Created by Martin on 2017/8/20.
 * Hello World
 */
class ShapeButton : Button {
    private var shapeType: Int = 0
    private var normalColor: Int = 0
    private var strokeWidth: Int = 0
    private var pressedSolidColor: Int = 0
    private var pressedStrokeColor: Int = 0
    private var strokeColor: Int = 0
    private var connerRadius: Float = 0f

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs){
        val a = context.obtainStyledAttributes(attrs, R.styleable.ShapeButton)
        handleAttrs(a)
        initialize()
    }

    private fun handleAttrs(a: TypedArray) {
        shapeType = a.getInt(R.styleable.ShapeButton_shapeType, GradientDrawable.RECTANGLE)
        normalColor = a.getColor(R.styleable.ShapeButton_solidColor, Color.LTGRAY)
        pressedSolidColor = a.getColor(R.styleable.ShapeButton_pressedSolidColor, Color.LTGRAY)
        strokeColor = a.getColor(R.styleable.ShapeButton_strokeColor, Color.LTGRAY)
        pressedStrokeColor = a.getColor(R.styleable.ShapeButton_pressedStrokeColor, Color.LTGRAY)
        strokeWidth = a.getDimensionPixelSize(R.styleable.ShapeButton_strokeWidth, 0)
        connerRadius = a.getDimensionPixelSize(R.styleable.ShapeButton_connerRadius, 0).toFloat()
        a.recycle()
    }


    private fun initialize() {
        isClickable = true
        background = createSelector()
    }

    private fun createSelector(): Drawable? {
        val stateDrawable = StateListDrawable()
        stateDrawable.addState(intArrayOf(android.R.attr.state_pressed, android.R.attr.state_enabled), createDrawable(android.R.attr.state_pressed))
        stateDrawable.addState(intArrayOf(), createDrawable(android.R.attr.state_enabled))
        return stateDrawable
    }

    @SuppressLint("WrongConstant")
    private fun createDrawable(state: Int): Drawable? {
        val drawable = GradientDrawable()
        drawable.shape = shapeType
        drawable.setColor(if (state == android.R.attr.state_pressed) pressedSolidColor else normalColor)
        drawable.setStroke(strokeWidth, if (state == android.R.attr.state_pressed) pressedStrokeColor else strokeColor)
        drawable.cornerRadius = connerRadius
        return drawable
    }
}