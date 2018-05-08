package com.github.bassaer.androidcolorpatterns

import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView

/**
 * Round Image view for picture on message
 * Created by nakayama on 2017/03/08.
 */
class RoundImageView : ImageView {
    private lateinit var mClipPath: Path

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
        setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        mClipPath = Path()
        val rect = RectF(0f, 0f, width.toFloat(), height.toFloat())
        val radius = resources.getDimensionPixelSize(R.dimen.radius_normal).toFloat()
        mClipPath.addRoundRect(rect, radius, radius, Path.Direction.CW)
    }

    override fun onDraw(canvas: Canvas) {
        initClipPath()
        canvas.clipPath(mClipPath)
        super.onDraw(canvas)
    }
}