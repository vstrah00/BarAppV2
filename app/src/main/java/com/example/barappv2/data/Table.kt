package com.example.barappv2.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tables")
data class Table(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val value: Float = 0f,
    val currentName: String? = null,
    val totalProfit: Float = 0f // You can add more fields as needed
)
