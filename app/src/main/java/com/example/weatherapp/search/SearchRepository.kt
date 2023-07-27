package com.example.weatherapp.search


import com.example.weatherapp.network.WeatherApiHelper
import javax.inject.Inject

class SearchRepository @Inject constructor(private val apiHelper: WeatherApiHelper) {
    fun getDetails(query: String) = apiHelper.getDetails(query)
    fun getProperties(query: String) = apiHelper.getProperties(query)
    fun getSevenDaysForecast(query: String, days: String) = apiHelper.getSevenDaysForecast(query, days)
    fun getHistory(query: String, date: String) = apiHelper.getHistory(query, date)

}