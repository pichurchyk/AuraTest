package com.pichurchyk.auratest.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pichurchyk.auratest.R
import com.pichurchyk.auratest.data.database.BootHistoryDBO
import com.pichurchyk.auratest.ui.ext.getPrettyDate

class BootListAdapter : ListAdapter<BootHistoryDBO, BootListAdapter.TextViewHolder>(DiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_boot, parent, false)
        return TextViewHolder(view)
    }

    override fun onBindViewHolder(holder: TextViewHolder, position: Int) {
        holder.textView.text = getItem(position).getPrettyDate()
    }

    private class DiffCallback : DiffUtil.ItemCallback<BootHistoryDBO>() {
        override fun areItemsTheSame(oldItem: BootHistoryDBO, newItem: BootHistoryDBO): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: BootHistoryDBO, newItem: BootHistoryDBO): Boolean {
            return oldItem.timestamp == newItem.timestamp
        }
    }

    inner class TextViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.tvItemDate)
    }
}