package com.github.bassaer.androidcolorpatterns

import android.os.Bundle
import android.support.annotation.StyleableRes
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import kotlinx.coroutines.experimental.launch

/**
 * Setting colors
 * Created by nakayama on 2018/05/04.
 */
class SettingActivity : AppCompatActivity() {

    var colors: HashMap<String, MutableList<Color>> = HashMap()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actvity_setting)

        loadColors()

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragment_container, SettingListFragment())
        transaction.commit()

        val backButton = findViewById<ImageView>(R.id.toolbar_icon_left)
        backButton.setOnClickListener {
            finish()
        }

    }

    private fun loadColors() {

        launch {
            colors = HashMap()
            @StyleableRes val index = 1
            val colorNames = resources.obtainTypedArray(R.array.colors)
            for (i in 0..colorNames.length()) {
                val resId = colorNames.getResourceId(i, -1)
                if (resId < 0) {
                    break
                }
                val colorArray = resources.obtainTypedArray(resId)

                val colorId = colorArray.getResourceId( index, -1)

                if (colorId < 0) {
                    colorArray.recycle()
                    break
                }

                val list: MutableList<Color> = mutableListOf()

                val ids = resources.obtainTypedArray(colorId)
                (0 until ids.length()).mapTo(list) {
                    val name = resources.getResourceEntryName(ids.getResourceId(it, -1))
                    Color(
                            name.toUpperCase().replace("_".toRegex(), " "),
                            ids.getColor(it, -1)
                    )
                }
                colors.put(colorArray.getString(0), list)
                colorArray.recycle()
                ids.recycle()
            }

            colorNames.recycle()
        }

    }
}