package com.example.moodcheck.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "moods")
data class Mood(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val rating: Int = 0,
    val year: Int = 2003,
    val month: String = "Jan",
    val day: Int = 0,
    val journal: String = ""
)
