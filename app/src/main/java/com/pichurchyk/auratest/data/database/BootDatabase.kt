package com.pichurchyk.auratest.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [BootHistoryDBO::class], version = 1, exportSchema = false)
internal abstract class BootDatabase : RoomDatabase() {
    abstract fun dao(): BootDao
}

internal fun BootDatabase(applicationContext: Context): BootDatabase {
    return Room.databaseBuilder(
        applicationContext,
        BootDatabase::class.java,
        "BootDatabase"
    ).build()
}