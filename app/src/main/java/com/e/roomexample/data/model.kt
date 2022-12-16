package com.e.roomexample.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "entries")
data class model(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int?=null,
    @ColumnInfo(name = "td")   val td: Int,
    @ColumnInfo(name = "md") val md: Int
)