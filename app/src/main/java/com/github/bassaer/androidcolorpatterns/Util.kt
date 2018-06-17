package com.github.bassaer.androidcolorpatterns

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.support.v4.graphics.drawable.DrawableCompat

/**
 * Utility
 * Created by nakayama on 2018/06/17.
 */
object Util {
    fun getColoredDrawable(context: Context, color: Int, iconId: Int): Drawable {
        val colorStateList = ColorStateList.valueOf(color)
        val icon = ContextCompat.getDrawable(context, iconId)
        val wrappedDrawable = DrawableCompat.wrap(icon)
        DrawableCompat.setTintList(wrappedDrawable, colorStateList)
        return wrappedDrawable
    }
}