package com.github.bassaer.androidcolorpatterns

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
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
        val icon = findViewById<ImageView>(R.id.toolbar_icon)
        icon.drawable.setTint(MDColor.WHITE)
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.background.setTint(MDColor.RED_500)
    }

}
