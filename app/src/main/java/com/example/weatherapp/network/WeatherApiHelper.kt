package com.example.weatherapp.network

import com.example.weatherapp.details.CurrentWeather
import com.example.weatherapp.history.History
import com.example.weatherapp.search.SearchItem
import kotlinx.coroutines.Deferred

interface WeatherApiHelper {
    fun getDetails(query: String?): Deferred<CurrentWeather>

    fun getProperties(query: String?): Deferred<List<SearchItem>>

    fun getSevenDaysForecast(query: String?, days: String): Deferred<CurrentWeather>

    fun getHistory(query: String?, date: String): Deferred<History>
}