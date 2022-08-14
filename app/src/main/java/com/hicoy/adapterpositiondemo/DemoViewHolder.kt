package com.hicoy.adapterpositiondemo

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DemoViewHolder(
    itemView1: View
): RecyclerView.ViewHolder(itemView1) {

    private val textView = itemView.findViewById<TextView>(R.id.text_view)

    fun bind(data: Data, pos: Int) {
        textView.text = "data: ${data.text}, \nadapterPosition: $bindingAdapterPosition, \nlayoutPosition: $layoutPosition, \nAbsoluteAdapterPosition: $absoluteAdapterPosition"
    }
}