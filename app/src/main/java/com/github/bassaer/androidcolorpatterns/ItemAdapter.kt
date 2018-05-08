package com.github.bassaer.androidcolorpatterns

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView

/**
 * Color list adapter
 * Created by nakayama on 2018/05/05.
 */
class ItemAdapter(context: Context, resource: Int, list: MutableList<Color>)
    : ArrayAdapter<Color>(context, resource, list) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val color = getItem(position) as Color
        lateinit var holder: ViewHolder
        lateinit var view: View
        convertView?.let {
            view = convertView
            holder = it.tag as ViewHolder
        } ?: let {
            view = LayoutInflater.from(context).inflate(R.layout.entity_color, parent, false)
            holder = ViewHolder()
            view.tag = holder
            holder.icon = view.findViewById(R.id.color_icon)
            holder.name = view.findViewById(R.id.color_name)
            holder.value = view.findViewById(R.id.color_value)
            holder.checkbox = view.findViewById(R.id.color_checkbox)
        }

        holder.icon?.setBackgroundColor(color.value)
        holder.name?.text = color.name
        holder.value?.text = String.format("#%x", color.value)
        holder.checkbox?.isChecked = color.selected

        return view
    }

    inner class ViewHolder {
        var icon: ImageView? = null
        var name: TextView? = null
        var value: TextView? = null
        var checkbox: CheckBox? = null
    }

}