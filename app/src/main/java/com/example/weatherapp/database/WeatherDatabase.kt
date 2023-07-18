package com.example.weatherapp.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DatabaseEntities::class], version = 2, exportSchema = false)
abstract class Database : RoomDatabase() {

    abstract val databaseDao: DatabaseDao

    companion object {

    }

}