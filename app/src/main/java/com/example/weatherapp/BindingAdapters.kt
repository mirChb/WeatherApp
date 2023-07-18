package com.example.weatherapp

import android.graphics.Bitmap
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.details.DetailsAdapter
import com.example.weatherapp.details.CardViewLines
import com.example.weatherapp.search.SearchAdapter
import com.example.weatherapp.search.SearchApiStatus
import com.example.weatherapp.search.SearchItem

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<SearchItem>?) {
    val adapter = recyclerView.adapter as SearchAdapter
    adapter.submitList(data)
}

@BindingAdapter("listWeather")
fun bindGridRecyclerView(recyclerView: RecyclerView, data: List<CardViewLines>?) {
    val adapter = recyclerView.adapter as DetailsAdapter
    adapter.submitList(data)
}

@BindingAdapter("bitmap")
fun ImageView.setBitmap(bitmap: Bitmap?) {
    setImageBitmap(bitmap)
}

@BindingAdapter("searchApiStatus")
fun bindStatus(statusImageView: ImageView, status: SearchApiStatus?) {
    when(status) {
        SearchApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading)
        }

        SearchApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.baseline_wifi_off_24)
        }

        else -> {
            statusImageView.visibility = View.GONE
        }
    }
}