package com.example.moodcheck.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "moods")
data class Mood(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val rating: Int = 0,
    val year: Int,
    val month: String,
    val day: Int,
    val journal: String
)
