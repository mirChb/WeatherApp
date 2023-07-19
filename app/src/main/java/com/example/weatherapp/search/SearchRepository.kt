package com.example.weatherapp.search

import android.location.Location.distanceBetween
import android.util.Log
import com.example.weatherapp.database.DatabaseDao
import com.example.weatherapp.database.DatabaseEntities
import com.example.weatherapp.details.Location
import com.example.weatherapp.search.SearchResponse
import com.example.weatherapp.network.WeatherApiService
import kotlinx.coroutines.*
import java.util.*

class SearchRepository(private val weatherApiService: WeatherApiService) {

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

/*
class SearchRepository(
    private val weatherApiService: WeatherApiService,
    private val databaseDao: DatabaseDao
) {

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

    suspend fun saveToDatabase(databaseEntities: DatabaseEntities) {
        databaseDao.insert(databaseEntities)
    }

    suspend fun getFromDatabase(query: String): DatabaseEntities? {
        return databaseDao.get(query)
    }
}
*/
