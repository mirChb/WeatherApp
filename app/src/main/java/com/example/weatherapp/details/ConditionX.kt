package com.example.weatherapp.details

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ConditionX(
    val code: Int,
    val icon: String,
    val text: String
): Parcelable