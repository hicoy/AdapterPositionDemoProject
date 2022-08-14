package com.hicoy.adapterpositiondemo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class DemoAdapter(
    private val listener: OnItemClickListener
): ListAdapter<Data, DemoViewHolder>(diff) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DemoViewHolder {
        return DemoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.viewholder_demo, parent, false))
    }

    override fun onBindViewHolder(holder: DemoViewHolder, position: Int) {
        holder.bind(currentList[position], position)
        holder.itemView.setOnClickListener {
            listener.onItemClick(holder)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(viewHolder: RecyclerView.ViewHolder)
    }

    companion object {
        val diff = object : DiffUtil.ItemCallback<Data>() {
            override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
                return oldItem == newItem
            }
        }
    }
}