package com.example.syncofflinedata.Repository

import androidx.room.Insert
import com.example.syncofflinedata.ApiService
import com.example.syncofflinedata.DataDao
import com.example.syncofflinedata.DataEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataRepository @Inject constructor(
    private val dataDao: DataDao,
    private val apiService: ApiService
) {

    fun getUnSyncData(): Flow<List<DataEntity>> = dataDao.getUnSyncData()

    suspend fun insertData(dataEntity: DataEntity) {
        dataDao.insert(dataEntity)
    }



    suspend fun syncData() {

        val unSyncData: List<DataEntity> = dataDao.getUnSyncData().first()

        if (unSyncData.isEmpty()) {
            val response = apiService.syncData(unSyncData)
            if (response.isSuccessful) {
                unSyncData.forEach{
                    dataDao.markedAsSynced(it.id)
                }
            }
        }

    }
}