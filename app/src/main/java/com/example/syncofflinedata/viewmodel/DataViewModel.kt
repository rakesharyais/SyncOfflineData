package com.example.syncofflinedata.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.syncofflinedata.DataEntity
import com.example.syncofflinedata.Repository.DataRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

class DataViewModel @Inject  constructor(
    private val repository: DataRepository
) : ViewModel(){

    val unsyncedData: StateFlow<List<DataEntity>> = repository.getUnSyncData()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    val allData = repository.getUnSyncData()

    fun insertData(content: String) {
        viewModelScope.launch {
            repository.insertData(DataEntity(content = content))
        }
    }

    fun syncData() {
        viewModelScope.launch {
            repository.syncData()
        }
    }
}