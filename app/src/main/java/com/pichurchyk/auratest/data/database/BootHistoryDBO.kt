package com.pichurchyk.auratest.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "BootHistory")
data class BootHistoryDBO(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val timestamp: Long
)