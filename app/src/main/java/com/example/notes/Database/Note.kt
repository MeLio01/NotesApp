package com.example.notes.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note")
data class Note (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "title") var title: String? = null,
    @ColumnInfo(name = "content") var content: String? = null,
    @ColumnInfo(name = "date") var date: String? = null
)

