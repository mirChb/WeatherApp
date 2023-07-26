package com.example.weatherapp.history

import android.os.Parcelable
import com.example.weatherapp.details.Condition
import com.example.weatherapp.details.Forecast
import com.example.weatherapp.details.Hour
import com.example.weatherapp.details.Location
import kotlinx.parcelize.Parcelize

@Parcelize
data class History(
    val forecast: ForecastHistory,
    val location: LocationHistory
): Parcelable

@Parcelize
data class LocationHistory(
    val name:String,
    val region:String,
    val country:String
):Parcelable

@Parcelize
data class ForecastHistory(
    val forecastday: List<ForecastDaysHistory>
): Parcelable

@Parcelize
data class ForecastDaysHistory(
    val date: String,
    val day: DayHistory,
    val astro: AstroHistory,
    val hour: List<Hour>
): Parcelable


@Parcelize
data class DayHistory(
    val maxtemp_c: Double,
    val mintemp_c: Double,
    val condition: Condition
):Parcelable{
    val min_temp
        get() = "Min Temp: ${mintemp_c}°C"

    val max_temp
        get() = "Max Temp: ${maxtemp_c}°C"
}

@Parcelize
data class AstroHistory(
    val sunrise:String,
    val sunset:String
):Parcelable{
}