package com.example.weatherapp.sevenDaysForecast

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SevenDaysCardViewText (
    val date: String,
    val propertyMin: String,
    val propertyMax: String
): Parcelable