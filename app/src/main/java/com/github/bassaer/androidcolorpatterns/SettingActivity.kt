package com.github.bassaer.androidcolorpatterns

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageButton

/**
 * Created by nakayama on 2018/05/04.
 */
class SettingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actvity_setting)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragment_container, SettingFragment())
        transaction.commit()

        val doneButton = findViewById<ImageButton>(R.id.toolbar_icon_right)
        doneButton.setOnClickListener {
            finish()
        }

    }
}