package com.example.weatherapp.details

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Forecastday(
    val astro: Astro,
    val date: String,
    val date_epoch: Int,
    val day: Day,
    val hour: List<Hour>
): Parcelable