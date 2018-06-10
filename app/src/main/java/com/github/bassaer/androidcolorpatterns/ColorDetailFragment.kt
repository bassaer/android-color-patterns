package com.github.bassaer.androidcolorpatterns

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

/**
 * Selecting color
 * Created by nakayama on 2018/05/04.
 */
class ColorDetailFragment : Fragment(), AdapterView.OnItemClickListener {


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
        listView?.onItemClickListener = this

        val backButton = activity.findViewById<ImageView>(R.id.toolbar_icon_left)
        backButton.setOnClickListener {
            fragmentManager.popBackStack()
        }

        if (arguments != null && arguments[ColorListFragment.KEY] != null) {
            val toolbar = activity.findViewById<TextView>(R.id.toolbar_title)
            toolbar.text = arguments[ColorListFragment.KEY].toString()
        }

        return rootView
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val checkbox = view?.findViewById<CheckBox>(R.id.color_checkbox)
        checkbox?.isChecked = if (checkbox != null) !checkbox.isChecked else false

    }

}