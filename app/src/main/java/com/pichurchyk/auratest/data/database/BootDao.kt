package com.pichurchyk.auratest.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BootDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveBootInfo(bootInfo: BootHistoryDBO)

    @Query("SELECT * FROM BootHistory")
    fun getAllBoots(): List<BootHistoryDBO>
}