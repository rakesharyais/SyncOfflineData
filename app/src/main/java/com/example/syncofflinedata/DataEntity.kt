package com.example.syncofflinedata

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "data_table")
data class DataEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val content: String,
    val isSynced: Boolean = false

)

