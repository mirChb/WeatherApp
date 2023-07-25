package com.example.weatherapp.search

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SearchItem(
    val id: Int,
    val name: String,
    val region: String,
    val country: String,
    val lat: Double,
    val lon: Double,
    val url: String
): Parcelable {
    val fullname
        get() = "$name, $region, $country"
}

@Parcelize
class SearchResponse(val locations: List<SearchItem>) : Parcelable
