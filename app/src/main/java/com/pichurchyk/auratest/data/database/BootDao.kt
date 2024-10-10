package com.pichurchyk.auratest.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BootDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveBootInfo(bootInfo: BootHistoryDBO)

    @Query("SELECT * FROM BootHistory")
    fun getAllBoots(): Flow<List<BootHistoryDBO>>

    @Query("SELECT * FROM BootHistory ORDER BY timestamp DESC LIMIT 2")
    suspend fun getLastTwoBoots(): List<BootHistoryDBO>
}