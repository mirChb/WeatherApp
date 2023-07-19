package com.example.weatherapp.details

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.search.SearchItem

class WeatherDetailsViewModelFactory(private val searchItem: SearchItem, private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeatherDetailsViewModel::class.java)) {
            return WeatherDetailsViewModel(searchItem, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}