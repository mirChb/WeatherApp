package com.example.weatherapp.details

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CardViewText(
    val drawbleDetails: DrawableDetails,
    val property: String,
    val property_detail: String?
) : Parcelable