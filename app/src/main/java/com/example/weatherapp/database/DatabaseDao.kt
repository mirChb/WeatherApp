package com.example.weatherapp.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DatabaseDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(databaseEntities: DatabaseEntities)

    @Update
    suspend fun update(databaseEntities: DatabaseEntities)

    @Query("DELETE FROM databaseTable")
    suspend fun clear()

    @Query("SELECT * FROM databaseTable WHERE name = :query")
    suspend fun get(query: String): DatabaseEntities?

    @Query("DELETE * FROM databaseTable WHERE name = :query")
    suspend fun clear_row(query: String)

    @Query("SELECT * FROM databaseTable WHERE name = :query ORDER BY desc")
    suspend fun getAllDays(query: String): LiveData<List<DatabaseEntities>>
}