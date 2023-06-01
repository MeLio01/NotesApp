package com.example.notes.Database

import android.graphics.Color
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlin.random.Random

@Entity(tableName = "note")
data class Note (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "title") var title: String? = null,
    @ColumnInfo(name = "content") var content: String? = null,
    @ColumnInfo(name = "date") var date: String? = null,
    @ColumnInfo(name = "color") var color: String? = getRandomColor()
)
{
    companion object {
        private val colorPalette = arrayOf("#6b705c", "#a5a58d", "#b7b7a4", "#ffe8d6", "#ddbea9", "#cb997e")

        fun getRandomColor(): String {
            val randomIndex = Random.nextInt(colorPalette.size)
            return colorPalette[randomIndex]
        }
    }
}