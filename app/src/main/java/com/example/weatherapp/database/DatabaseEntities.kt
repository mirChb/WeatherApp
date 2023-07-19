package com.example.weatherapp.database

import androidx.room.Entity

@Entity(tableName="databaseTable", primaryKeys=["date", "name"])
class DatabaseEntities (
    val date: String,
    val name: String,
    val url: String,
    val currentTemp_c: String,
    val minTemp_c: String,
    val maxTemp_c: String,
    val sunrise: String,
    val sunset: String,
    val wind: String,
    val pressure: String,
    val humidity: String,
    val createdBy: String
)