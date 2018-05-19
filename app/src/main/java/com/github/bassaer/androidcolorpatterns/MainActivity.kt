package com.github.bassaer.androidcolorpatterns

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView
import com.github.bassaer.androidcolorpatterns.R.array.colors
import com.github.bassaer.library.MDColor
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        toolbar.setBackgroundColor(MDColor.ORANGE_500)
        window.statusBarColor = MDColor.ORANGE_700

        val title = findViewById<TextView>(R.id.toolbar_title)
        title.setTextColor(MDColor.WHITE
        )
        val settingButton = findViewById<ImageButton>(R.id.toolbar_icon)
        settingButton.drawable.setTint(MDColor.WHITE)
        settingButton.setOnClickListener {
            val intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.background.setTint(MDColor.RED_500)

        hoge()
    }

    fun hoge() {
        val colorNames = resources.obtainTypedArray(R.array.colors)
        for (i in 0..colorNames.length()) {
            val resId = colorNames.getResourceId(i, -1)
            if (resId < 0) {
                break
            }
            val colorArray = resources.obtainTypedArray(resId)

            val colorId = colorArray.getResourceId(1, -1)

            if (colorId < 0) {
                colorArray.recycle()
                break
            }

            val ids = resources.obtainTypedArray(colorId)
            for (j in 0 until ids.length()) {
                Log.d("HOGE", "now " + j)
                Log.d("HOGE",  resources.getResourceEntryName(ids.getResourceId(j, -1))+ " -> "
                        + String.format("#%x",ids.getInteger(j, -1)))
            }
            colorArray.recycle()
            ids.recycle()
        }

        colorNames.recycle()
    }


}
