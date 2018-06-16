package com.github.bassaer.androidcolorpatterns

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView

/**
 * List of setting color,
 * Created by nakayama on 2018/05/20.
 */
class SettingListFragment : Fragment() {

    companion object {
        const val KEY = "ColorType"
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater?.inflate(R.layout.fragment_setting, container, false)
        val listView = rootView?.findViewById<ListView>(R.id.setting_list)
        val list = listOf(
                ColorManager.COLOR_PRIMARY,
                ColorManager.COLOR_PRIMARY_DARK,
                ColorManager.COLOR_ACCENT
        )
        val adapter = ArrayAdapter(context, android.R.layout.simple_list_item_1, list)
        listView?.adapter = adapter

        listView?.setOnItemClickListener { parent, _, position, _ ->
            val bundle = Bundle()
            val type = parent.getItemAtPosition(position) as String
            bundle.putString(SettingListFragment.KEY, type)
            val fragment = ColorListFragment()
            fragment.arguments = bundle
            val transaction = fragmentManager.beginTransaction()
            transaction.setCustomAnimations(
                    R.animator.slide_in_from_right,
                    R.animator.slide_out_to_left,
                    R.animator.slide_in_from_left,
                    R.animator.slide_out_to_right
            )
                    .replace(R.id.fragment_container, fragment)
                    .addToBackStack(javaClass.name)
                    .commit()
        }

        val backButton = activity.findViewById<ImageView>(R.id.toolbar_icon_left)
        backButton.setOnClickListener {
            activity.finish()
        }

        val toolbar = activity.findViewById<TextView>(R.id.toolbar_title)
        toolbar.text = getString(R.string.color_type)
        return rootView
    }
}