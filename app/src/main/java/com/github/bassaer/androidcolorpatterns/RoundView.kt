package com.github.bassaer.androidcolorpatterns

import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.graphics.RectF
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.View



/**
 * Round Image view for picture on message
 * Created by nakayama on 2017/03/08.
 */
class RoundView : View {
    private lateinit var mClipPath: Path
    private var radius = 0f

    constructor(context: Context) : super(context) {
        initClipPath()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initClipPath()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initClipPath()
    }

    private fun initClipPath() {
        // Turn off hardware acceleration to use Canvas#clipPath()
        setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        mClipPath = Path()
        val rate = 0.8f
        val rect = RectF(0f, 0f, width.toFloat() * rate, height.toFloat() * rate)
        radius = resources.getDimensionPixelSize(R.dimen.radius_normal).toFloat()
        //radius = width.toFloat() / 2
        mClipPath.addRoundRect(rect, radius, radius, Path.Direction.CW)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        initClipPath()
    }

    override fun onDraw(canvas: Canvas) {
        canvas.clipPath(mClipPath)
        super.onDraw(canvas)
    }

    override fun setBackgroundColor(color: Int) {
        val gradientDrawable = GradientDrawable()
        gradientDrawable.cornerRadius = radius
        gradientDrawable.setColor(color)
        background = gradientDrawable
    }
}