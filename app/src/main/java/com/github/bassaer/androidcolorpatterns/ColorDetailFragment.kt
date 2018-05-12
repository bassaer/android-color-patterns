package com.github.bassaer.androidcolorpatterns

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.Toast
import com.github.bassaer.library.MDColor

/**
 * Created by nakayama on 2018/05/04.
 */
class ColorDetailFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (arguments == null || arguments[ColorListFragment.KEY] == null) {
            Toast.makeText(context, getString(R.string.error), Toast.LENGTH_SHORT).show()
            activity.finish()
        }

        val rootView = inflater?.inflate(R.layout.fragment_setting, container, false)
        val listView = rootView?.findViewById<ListView>(R.id.setting_list)


        val colorList = getColorList()

        val adapter = ItemAdapter(context, 0, colorList)
        listView?.adapter = adapter

        return rootView
    }

    private fun getColorList(): MutableList<Color> {
        val list: MutableList<Color> = mutableListOf()
        val iterator = MDColor.sMDColorNameMap.keys.iterator()
        while (iterator.hasNext()) {
            val key = iterator.next() as String
            val name = key.let {
                it.toUpperCase().replace("_".toRegex(), " ")
            }
            val value = MDColor.sMDColorNameMap[key] as Int
            list.add(Color(name, value, false))
        }
        return list
    }
}