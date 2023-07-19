package com.example.weatherapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DatabaseEntities::class], version = 2, exportSchema = false)
abstract class WeatherDatabase : RoomDatabase() {

    abstract val databaseDao: DatabaseDao

    companion object {

        @Volatile  // visible to other threads
        private var INSTANCE: WeatherDatabase? = null

        fun getInstane(context: Context): WeatherDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(context.applicationContext,WeatherDatabase::class.java, "weather_database")
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }

                return instance
            }
        }

    }

}