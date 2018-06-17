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
class ColorDetailFragment : Fragment() {

    private val colorType: String by lazy {
        if (arguments == null || arguments[SettingListFragment.KEY] == null) {
            ""
        } else {
            arguments[SettingListFragment.KEY] as String
        }
    }

    private var selectedColor: Int = Int.MAX_VALUE

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (arguments == null || arguments[ColorListFragment.KEY] == null ||
                arguments[SettingListFragment.KEY] == null ) {
            Toast.makeText(context, getString(R.string.error), Toast.LENGTH_SHORT).show()
            activity.finish()
        }
        val key = arguments.getString(ColorListFragment.KEY)
        val type = arguments.getString(SettingListFragment.KEY)

        val rootView = inflater?.inflate(R.layout.fragment_setting, container, false)
        val listView = rootView?.findViewById<ListView>(R.id.setting_list)

        val colorList = (activity as SettingActivity).colors[key] ?: mutableListOf()

        val adapter = ItemAdapter(context, 0, colorList, type)
        listView?.adapter = adapter
        listView?.onItemClickListener = AdapterView.OnItemClickListener {parent, view, position, _ ->
            val checkbox = view?.findViewById<CheckBox>(R.id.color_checkbox)
            checkbox?.isChecked = if (checkbox != null) !checkbox.isChecked else false
            selectedColor = (parent.getItemAtPosition(position) as Color).value
            adapter.selectColor(color = selectedColor)
        }

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

    override fun onPause() {
        super.onPause()
        if (selectedColor == Int.MAX_VALUE || colorType.isEmpty()) {
            return
        }
        val manager = ColorManager(context)
        when(colorType) {
            ColorManager.COLOR_PRIMARY -> {
                manager.setPrimary(selectedColor)
            }
            ColorManager.COLOR_PRIMARY_DARK -> {
                manager.setPrimaryDark(selectedColor)
            }
            ColorManager.COLOR_ACCENT -> {
                manager.setAccent(selectedColor)
            }
            ColorManager.TEXT_COLOR_PRIMARY -> {
                manager.setTextColorPrimary(selectedColor)
            }
            ColorManager.TEXT_COLOR_SECONDARY -> {
                manager.setTextColorSecondary(selectedColor)
            }
            ColorManager.FAB_ICON -> {
                manager.setFabIcon(selectedColor)
            }
        }
    }
}