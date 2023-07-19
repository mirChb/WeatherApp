package com.example.weatherapp.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.WeatherDetailsItemBinding

class DetailsAdapter() : ListAdapter<CardViewLines, DetailsAdapter.CardViewHolder>(DiffCallback) {

    companion object DiffCallback: DiffUtil.ItemCallback<CardViewLines>() {
        override fun areItemsTheSame(oldItem: CardViewLines, newItem: CardViewLines): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: CardViewLines, newItem: CardViewLines): Boolean {
            return oldItem.property == newItem.property
        }
    }

    class CardViewHolder(private var binding: WeatherDetailsItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(cardViewLines: CardViewLines) {
            binding.cardViewLines = cardViewLines
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        return CardViewHolder(WeatherDetailsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}