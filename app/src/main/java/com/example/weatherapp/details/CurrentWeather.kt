package com.example.weatherapp.details

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CurrentWeather(
    val current: Current,
    val location: Location,
    val forecast: Forecast
): Parcelable

