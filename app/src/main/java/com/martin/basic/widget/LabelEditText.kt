package com.martin.basic.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.widget.EditText
import com.martin.basic.R
import android.graphics.Paint.FontMetricsInt


/**
 * Created by Martin on 2017/9/3.
 * Hello World
 */
class LabelEditText(context: Context?, attrs: AttributeSet?)
    : EditText(context, attrs) {
    private var labelText: String? = null

    private var labelTextColor: Int = Color.WHITE

    private var labelTextSize: Float = 14f

    private var labelBackground: Int = Color.YELLOW

    private var labelBackgroundRadius: Float = 0f

    private var labelPadding: Int = 12

    private var labelInsertSize: Int = 10

    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val targetRect: RectF

    private var baselineY: Float = 0.0f

    init {
        initAttrs(context!!, attrs!!)
        targetRect = RectF()
    }



    private fun initAttrs(context: Context, attrs: AttributeSet) {
        val arrays = context.obtainStyledAttributes(attrs, R.styleable.LabelEditText)
        labelText = arrays.getString(R.styleable.LabelEditText_labelText)
        labelTextColor = arrays.getColor(R.styleable.LabelEditText_labelTextColor, labelTextColor)
        labelTextSize = arrays.getDimension(R.styleable.LabelEditText_labelTextSize, labelTextSize)
        labelBackground = arrays.getColor(R.styleable.LabelEditText_labelBackground, labelBackground)
        labelBackgroundRadius = arrays.getDimension(R.styleable.LabelEditText_labelBackgroundRadius, labelBackgroundRadius)
        labelPadding = arrays.getDimensionPixelSize(R.styleable.LabelEditText_labelPadding, labelPadding)
        labelInsertSize = arrays.getDimensionPixelSize(R.styleable.LabelEditText_labelInsertSize, labelInsertSize)
        arrays.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        measureLabelSize()
    }

    private var labelTextWidth: Float = 0f

    private fun measureLabelSize() {
        if (labelText == null) return
        mPaint.textSize = labelTextSize.toFloat()
        labelTextWidth = mPaint.measureText(labelText)
        val fontMetrics = paint.fontMetricsInt
        // 转载请注明出处：http://blog.csdn.net/hursing
        targetRect.set(0f, 0f, labelTextWidth + labelInsertSize * 2, measuredHeight.toFloat())
        baselineY = (targetRect.bottom + targetRect.top - fontMetrics.bottom - fontMetrics.top) / 2
    }

    override fun onDraw(canvas: Canvas) {
        val flag = drawLabel(canvas)
        if (flag) {
            canvas.save()
            canvas.translate(targetRect.width() + labelPadding, 0f)
            super.onDraw(canvas)
            canvas.restore()
        } else {
            super.onDraw(canvas)
        }
    }

    private fun drawLabel(canvas: Canvas): Boolean {
        /**绘制背景*/
        if (labelText == null) return false
        mPaint.style = Paint.Style.FILL
        mPaint.color = labelBackground
        canvas.drawRoundRect(targetRect, labelBackgroundRadius, labelBackgroundRadius, mPaint)
        mPaint.color = labelTextColor
        mPaint.textSize = labelTextSize
        canvas.drawText(labelText, (targetRect.width() - labelTextWidth) / 2, baselineY, mPaint)
        return true
    }

}