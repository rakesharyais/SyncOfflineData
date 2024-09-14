package com.example.syncofflinedata

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface DataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(dataEntity: DataEntity)

    @Query("SELECT  * FROM data_table WHERE IsSynced = 0")
    fun getUnSyncData(): Flow<List<DataEntity>>

    @Query("UPDATE data_table SET isSynced = 1 WHERE id =:id")
    suspend fun markedAsSynced(id: Int)
}