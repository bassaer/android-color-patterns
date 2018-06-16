package com.github.bassaer.androidcolorpatterns

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.widget.ImageButton
import android.widget.TextView
import com.github.bassaer.library.MDColor
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val title = findViewById<TextView>(R.id.toolbar_title)
        title.setTextColor(MDColor.WHITE)
        val settingButton = findViewById<ImageButton>(R.id.toolbar_icon)
        settingButton.drawable.setTint(MDColor.WHITE)
        settingButton.setOnClickListener {
            val intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        val manager = ColorManager(this)
        toolbar.setBackgroundColor(manager.getPrimary())
        window.statusBarColor = manager.getPrimaryDark()
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.background.setTint(manager.getAccent())
    }
}
