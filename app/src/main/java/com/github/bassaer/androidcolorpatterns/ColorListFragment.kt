package com.github.bassaer.androidcolorpatterns

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

/**
 * Base color name list
 * Created by nakayama on 2018/05/12.
 */
class ColorListFragment : Fragment() {

    companion object {
        const val KEY = "ColorName"
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater?.inflate(R.layout.fragment_setting, container, false)
        val listView = rootView?.findViewById<ListView>(R.id.setting_list)
        val colorNames = (activity as SettingActivity).colors.keys.toList()

        val adapter = ArrayAdapter(context, android.R.layout.simple_list_item_1, colorNames)
        listView?.adapter = adapter
        val bundle = Bundle()
        if (arguments != null && arguments[SettingListFragment.KEY] != null) {
            val toolbar = activity.findViewById<TextView>(R.id.toolbar_title)
            val colorType = arguments[SettingListFragment.KEY].toString()
            toolbar.text = colorType
            bundle.putString(SettingListFragment.KEY, colorType)
        }
        listView?.onItemClickListener = AdapterView.OnItemClickListener { parent, _, position, _ ->
            val name = parent.getItemAtPosition(position) as String
            bundle.putString(KEY, name)
            val fragment = ColorDetailFragment()
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
            fragmentManager.popBackStack()
        }

        return rootView
    }

    override fun onResume() {
        super.onResume()
        (activity as SettingActivity).update()
    }
}