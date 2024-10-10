package com.pichurchyk.auratest.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [BootHistoryDBO::class], version = 1, exportSchema = false)
abstract class BootDatabase : RoomDatabase() {

    abstract fun bootDao(): BootDao

    companion object {
        @Volatile
        private var INSTANCE: BootDatabase? = null

        fun getDatabase(context: Context): BootDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BootDatabase::class.java,
                    "boot_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}