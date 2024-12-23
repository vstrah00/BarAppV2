package com.example.barappv2.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.barappv2.data.AppDatabase
import com.example.barappv2.data.Table
import kotlinx.coroutines.launch

class TableViewModel(application: Application) : AndroidViewModel(application) {
    private val tableDao = AppDatabase.getDatabase(application).tableDao()
    val allTables: LiveData<List<Table>> = tableDao.getAllTables()
    val totalProfit: LiveData<Float> = tableDao.getTotalProfit()

    fun addTable(name: String) = viewModelScope.launch {
        val table = Table(name = name)
        Log.d("TableViewModel", "Adding table: $table")
        tableDao.insertTable(table)
    }

    fun updateTable(table: Table) = viewModelScope.launch {
        Log.d("TableViewModel", "Updating table: $table")
        tableDao.updateTable(table)
    }

    fun deleteTable(table: Table) = viewModelScope.launch {
        Log.d("TableViewModel", "Deleting table: $table")
        tableDao.deleteTable(table)
    }
}
