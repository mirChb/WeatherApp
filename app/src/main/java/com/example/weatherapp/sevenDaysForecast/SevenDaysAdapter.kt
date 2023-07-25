package com.example.weatherapp.sevenDaysForecast

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.SevenDaysItemBinding
import com.example.weatherapp.details.CurrentWeather

class SevenDaysAdapter() : ListAdapter<SevenDaysCardViewText, SevenDaysAdapter.SevenDaysViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<SevenDaysCardViewText>() {
        override fun areItemsTheSame(oldItem: SevenDaysCardViewText, newItem: SevenDaysCardViewText): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: SevenDaysCardViewText, newItem: SevenDaysCardViewText): Boolean {
            return oldItem == newItem
        }
    }

    class SevenDaysViewHolder(private var binding: SevenDaysItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(cardViewText: SevenDaysCardViewText) {
            binding.cardViewText = cardViewText
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): SevenDaysViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = SevenDaysItemBinding.inflate(layoutInflater, parent, false)
                return SevenDaysViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SevenDaysViewHolder {
        return SevenDaysViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: SevenDaysViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

