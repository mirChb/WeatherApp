package com.example.weatherapp.search


import com.example.weatherapp.network.WeatherApiService
import kotlinx.coroutines.*
import javax.inject.Inject

class SearchRepository @Inject constructor(private val weatherApiService: WeatherApiService) {

    var isFullyInitialized = false
        private set

    suspend fun getProperties(q: String): Deferred<List<SearchItem>> {
        val result = coroutineScope {
            val deferred = async { weatherApiService.getProperties(q) }
            deferred.await()
        }
        isFullyInitialized = true
        return result
    }
}
