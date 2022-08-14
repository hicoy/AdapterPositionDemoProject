package com.hicoy.adapterpositiondemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.delay

class MainActivity : AppCompatActivity() {

    private val adapter = ConcatAdapter()

    private val listener = object : DemoAdapter.OnItemClickListener{
        override fun onItemClick(viewHolder: RecyclerView.ViewHolder) {
            lifecycleScope.launchWhenStarted {
                val bindingAdapter = viewHolder.bindingAdapter as? ListAdapter<*, *> ?: return@launchWhenStarted
                if (viewHolder.bindingAdapterPosition < bindingAdapter.currentList.size - 1) {
                    Log.e("aaa","before move: layoutPosition: "+viewHolder.layoutPosition+" absoluteAdapterPosition: "+viewHolder.absoluteAdapterPosition)
                    viewHolder.bindingAdapter?.notifyItemMoved(viewHolder.bindingAdapterPosition, viewHolder.bindingAdapterPosition + 1)
                    Log.e("aaa","after move: layoutPosition:  "+viewHolder.layoutPosition+" absoluteAdapterPosition: "+viewHolder.absoluteAdapterPosition)
                    delay(20)
                    Log.e("aaa","after 20ms: layoutPosition:  "+viewHolder.layoutPosition+" absoluteAdapterPosition: "+viewHolder.absoluteAdapterPosition)
                }
            }
        }
    }
    private val adapterA = DemoAdapter(listener, "A")
    private val adapterB = DemoAdapter(listener, "B")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        adapter.addAdapter(adapterA)
        adapter.addAdapter(adapterB)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(baseContext, RecyclerView.VERTICAL, false)

        adapterA.submitList(
            createDemoData()
        )
        adapterB.submitList(
            createDemoData()
        )
    }

    private fun createDemoData(): MutableList<Data> {
        return mutableListOf<Data>().apply {
            repeat(10) {
                add(Data(it.toString()))
            }
        }
    }
}