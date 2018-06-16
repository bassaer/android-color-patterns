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
    }

    private val prefs: SharedPreferences by lazy {
        context.getSharedPreferences(javaClass.name, Context.MODE_PRIVATE)
    }

    fun getPrimary() = get(COLOR_PRIMARY, R.color.colorPrimary)

    fun getPrimaryDark() = get(COLOR_PRIMARY_DARK, R.color.colorPrimaryDark)

    fun getAccent() = get(COLOR_ACCENT, R.color.colorAccent)

    fun setPrimary(color: Int) = set(COLOR_PRIMARY, color)

    fun setPrimaryDark(color: Int) =set(COLOR_PRIMARY_DARK, color)

    fun setAccent(color: Int) = set(COLOR_ACCENT, color)

    private fun get(key: String, default: Int): Int {
        return prefs.getInt(key, ContextCompat.getColor(context, default))
    }

    private fun set(key: String, color: Int) {
        val editor = prefs.edit()
        editor.putInt(key, color)
        editor.apply()
    }
}