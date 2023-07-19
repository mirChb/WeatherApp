package com.example.weatherapp.details

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Forecast(
    val forecastday: List<Forecastday>
): Parcelable