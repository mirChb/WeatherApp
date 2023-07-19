package com.example.weatherapp.network

import com.example.weatherapp.details.CurrentWeather
import com.example.weatherapp.details.Location
import com.example.weatherapp.search.SearchItem
import com.example.weatherapp.search.SearchResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val BASE_URL = "http://api.weatherapi.com/v1/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface WeatherApiService {
    @GET("search.json?key=87f3ecafe1674392ac3143139230507")
    fun getProperties(@Query("q") query: String?): Deferred<List<SearchItem>>
        //return retrofit.create(WeatherApiService::class.java).getProperties(query)  // wait to see if correct


    @GET("forecast.json?key=87f3ecafe1674392ac3143139230507")
    fun getDetails(@Query("q") query: String?): Deferred<CurrentWeather>
}

object WeatherApi {
    val reftrofitService: WeatherApiService by lazy {
        retrofit.create(WeatherApiService::class.java)
    }
}