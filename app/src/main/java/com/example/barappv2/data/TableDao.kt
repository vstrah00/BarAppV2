package com.example.barappv2.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TableDao {
    @Query("SELECT * FROM tables")
    fun getAllTables(): LiveData<List<Table>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTable(table: Table)

    @Update
    suspend fun updateTable(table: Table)

    @Delete
    suspend fun deleteTable(table: Table)

    @Query("SELECT SUM(value) FROM tables")
    fun getTotalProfit(): LiveData<Float>
}
