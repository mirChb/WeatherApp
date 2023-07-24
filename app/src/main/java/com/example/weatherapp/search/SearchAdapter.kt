package com.example.weatherapp.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.ListItemBinding
import com.example.weatherapp.search.SearchAdapter.SearchViewHolder

class SearchAdapter(val clickListener: SearchClickListener) : ListAdapter<SearchItem, SearchViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<SearchItem>() {
        override fun areItemsTheSame(oldItem: SearchItem, newItem: SearchItem): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: SearchItem, newItem: SearchItem): Boolean {
            return oldItem == newItem
        }
    }

    class SearchViewHolder(private var binding: ListItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(listener: SearchClickListener, location: SearchItem) {
            binding.searchItem = location
            binding.clickListener = listener

            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): SearchViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemBinding.inflate(layoutInflater, parent, false)
                return SearchViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(clickListener, getItem(position))
    }
}

class SearchClickListener(val clickListener: (searchItem: SearchItem) -> Unit) {
    fun onClick(searchItem: SearchItem) = clickListener(searchItem)
}
