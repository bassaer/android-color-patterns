package com.github.bassaer.androidcolorpatterns

import android.content.Context
import android.content.SharedPreferences
import android.support.v4.content.ContextCompat

/**
 * ColorManager class provides saved color data
 * Created by nakayama on 2018/06/11.
 */
class ColorManager (val context: Context) {

    companion object {
        const val COLOR_PRIMARY = "Color Primary"
        const val COLOR_PRIMARY_DARK = "Color Primary Dark"
        const val COLOR_ACCENT = "Color Accent"
        const val TEXT_COLOR_PRIMARY = "Text Color Primary"
        const val TEXT_COLOR_SECONDARY = "Text Color Secondary"
        const val FAB_ICON = "FAB Icon"
    }

    private val prefs: SharedPreferences by lazy {
        context.getSharedPreferences(javaClass.name, Context.MODE_PRIVATE)
    }

    fun getPrimary() = get(COLOR_PRIMARY, R.color.colorPrimary)

    fun getPrimaryDark() = get(COLOR_PRIMARY_DARK, R.color.colorPrimaryDark)

    fun getAccent() = get(COLOR_ACCENT, R.color.colorAccent)

    fun getTextColorPrimary() = get(TEXT_COLOR_PRIMARY, R.color.textColorPrimary)

    fun getTextColorSecondary() = get(TEXT_COLOR_SECONDARY, R.color.textColorSecondary)

    fun getFabIcon() = get(FAB_ICON, R.color.fabIcon)

    fun setPrimary(color: Int) = set(COLOR_PRIMARY, color)

    fun setPrimaryDark(color: Int) = set(COLOR_PRIMARY_DARK, color)

    fun setAccent(color: Int) = set(COLOR_ACCENT, color)

    fun setTextColorPrimary(color: Int) = set(TEXT_COLOR_PRIMARY, color)

    fun setTextColorSecondary(color: Int) = set(TEXT_COLOR_SECONDARY, color)

    fun setFabIcon(color: Int) = set(FAB_ICON, color)

    private fun get(key: String, default: Int): Int {
        return prefs.getInt(key, ContextCompat.getColor(context, default))
    }

    private fun set(key: String, color: Int) {
        val editor = prefs.edit()
        editor.putInt(key, color)
        editor.apply()
    }
}