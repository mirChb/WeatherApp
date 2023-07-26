package com.example.weatherapp.history

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HistoryCardView (
    val date: String,
    val propertyTemp: String,
    val icon: String
): Parcelable