package com.example.syncofflinedata

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DataEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun dataDoa(): DataDao
}