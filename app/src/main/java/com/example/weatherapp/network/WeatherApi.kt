package com.example.weatherapp.network

import com.example.weatherapp.details.CurrentWeather
import com.example.weatherapp.history.History
import com.example.weatherapp.search.SearchItem
import kotlinx.coroutines.Deferred
import javax.inject.Inject

class WeatherApi @Inject constructor(
    private val retrofitService: WeatherApiService
): WeatherApiHelper {
    override fun getDetails(query: String?): Deferred<CurrentWeather> = retrofitService.getDetails(query)

    override fun getProperties(query: String?): Deferred<List<SearchItem>> = retrofitService.getProperties(query)

    override fun getSevenDaysForecast(query: String?, days: String): Deferred<CurrentWeather> = retrofitService.getSevenDaysForecast(query, days)

    override fun getHistory(query: String?, date: String): Deferred<History> = retrofitService.getHistory(query, date)
}