package com.example.weatherapp.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.HistoryItemBinding

class HistoryAdapter(): ListAdapter<HistoryCardView, HistoryAdapter.HistoryViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<HistoryCardView>() {
        override fun areItemsTheSame(oldItem: HistoryCardView, newItem: HistoryCardView): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: HistoryCardView, newItem: HistoryCardView): Boolean {
            return oldItem.date == newItem.date
        }
    }

    class HistoryViewHolder(private var binding: HistoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(history: HistoryCardView) {
            binding.cardView = history
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        return HistoryViewHolder(HistoryItemBinding.inflate(LayoutInflater.from(parent.context),
            parent, false))
    }


    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}