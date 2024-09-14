package com.example.syncofflinedata.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.filedownloader.databinding.ItemDataBinding
import com.example.syncofflinedata.DataEntity


class DataAdapter : ListAdapter<DataEntity, DataAdapter.DataViewHolder>(DataDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding = ItemDataBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return DataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val dataItem = getItem(position)
        holder.bind(dataItem)
    }

    class DataViewHolder(private val binding: ItemDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(dataEntity: DataEntity) {
            // Bind the data to the UI components
            //binding.textViewTitle.text = dataEntity.title
            binding.textViewContent.text = dataEntity.content
        }
    }

    // DiffUtil class to optimize list changes
    class DataDiffCallback : DiffUtil.ItemCallback<DataEntity>() {
        override fun areItemsTheSame(oldItem: DataEntity, newItem: DataEntity): Boolean {
            return oldItem.id == newItem.id // Compare unique IDs of the items
        }

        override fun areContentsTheSame(oldItem: DataEntity, newItem: DataEntity): Boolean {
            return oldItem == newItem // Compare contents of the items
        }
    }
}