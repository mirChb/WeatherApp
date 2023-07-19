package com.example.weatherapp.details

import android.graphics.Bitmap
import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import androidx.annotation.DrawableRes

@Parcelize
data class CardViewLines (
    val drawable: Bitmap,
    val property: String,
    val property_detail: String?
): Parcelable