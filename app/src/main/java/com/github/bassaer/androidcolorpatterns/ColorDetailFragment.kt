package com.github.bassaer.androidcolorpatterns

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.Toast

/**
 * Created by nakayama on 2018/05/04.
 */
class ColorDetailFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (arguments == null || arguments[ColorListFragment.KEY] == null) {
            Toast.makeText(context, getString(R.string.error), Toast.LENGTH_SHORT).show()
            activity.finish()
        }
        val key = arguments[ColorListFragment.KEY]

        val rootView = inflater?.inflate(R.layout.fragment_setting, container, false)
        val listView = rootView?.findViewById<ListView>(R.id.setting_list)


        val colorList = (activity as SettingActivity).colors[key] ?: mutableListOf()

        val adapter = ItemAdapter(context, 0, colorList)
        listView?.adapter = adapter

        return rootView
    }

}