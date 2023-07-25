package com.example.weatherapp

import android.graphics.Bitmap
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.details.DetailsAdapter
import com.example.weatherapp.details.CardViewText
import com.example.weatherapp.details.DrawableDetails
import com.example.weatherapp.search.SearchAdapter
import com.example.weatherapp.search.SearchApiStatus
import com.example.weatherapp.search.SearchItem
import com.example.weatherapp.sevenDaysForecast.SevenDaysAdapter
import com.example.weatherapp.sevenDaysForecast.SevenDaysCardViewText

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<SearchItem>?) {
    val adapter = recyclerView.adapter as SearchAdapter
    adapter.submitList(data)
}

@BindingAdapter("listWeather")
fun bindGridRecyclerView(recyclerView: RecyclerView, data: List<CardViewText>?) {
    val adapter = recyclerView.adapter as DetailsAdapter
    adapter.submitList(data)
}

@BindingAdapter("listWeather")
fun bindSevenDaysList(recyclerView: RecyclerView, list: LiveData<List<SevenDaysCardViewText>>?) {
    list?.let {
        val adapter = recyclerView.adapter as SevenDaysAdapter
        adapter.submitList(it.value)
    }
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

@BindingAdapter("weatherVectorDrawableDetails")
fun chooseDrawable(drawableImageView: ImageView, drawableDetails: DrawableDetails) {
    when(drawableDetails) {
        DrawableDetails.SUNRISE -> {
            drawableImageView.setImageResource(R.drawable.baseline_wb_sunny_24)
        }

        DrawableDetails.SUNSET -> {
            drawableImageView.setImageResource(R.drawable.baseline_nightlight_24)
        }

        DrawableDetails.WIND -> {
            drawableImageView.setImageResource(R.drawable.baseline_air_24)
        }

        DrawableDetails.PRESSURE -> {
            drawableImageView.setImageResource(R.drawable.baseline_vertical_align_center_24)
        }

        DrawableDetails.HUMIDITY -> {
            drawableImageView.setImageResource(R.drawable.baseline_water_drop_24)
        }

        else -> {
            drawableImageView.setImageResource(R.drawable.baseline_info_24)
        }
    }
}

@BindingAdapter("app:src")
fun setImageViewDrawable(imageView: ImageView, drawable: DrawableDetails?) {
    if (drawable != null) {
        chooseDrawable(imageView, drawable)
    } else {
        Log.i("BindingAdapters", "in app:src")
    }
}
