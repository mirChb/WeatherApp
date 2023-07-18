package com.example.weatherapp.details

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Current(
    val cloud: Int,
    val condition: Condition,
    val feelslike_c: Double,
    val feelslike_f: Double,
    val gust_kph: Double,
    val gust_mph: Double,
    val humidity: Int,
    val is_day: Int,
    val last_updated: String,
    val last_updated_epoch: Int,
    val precip_in: Double,
    val precip_mm: Double,
    val pressure_in: Double,
    val pressure_mb: Double,
    val temp_c: Double,
    val temp_f: Double,
    val uv: Double,
    val vis_km: Double,
    val vis_miles: Double,
    val wind_degree: Int,
    val wind_dir: String,
    val wind_kph: Double,
    val wind_mph: Double
): Parcelable {
    val last_updated_string
        get() = "Updated at: $last_updated"

    val temp_c_string
        get() = "${temp_c}°C"

    val wind_kph_string
        get() = "${wind_kph} kph"

    val pressure_mb_string
        get() = "$pressure_mb mb"

    val humidity_string
        get() = "${humidity}%"
}