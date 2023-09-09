package com.example.moodcheck.data

import kotlinx.coroutines.flow.Flow

interface MoodsRepository {
    fun getMonths(): Flow<Map<String, List<Mood>>>

    fun getMood(id: Int): Flow<Mood?>

    suspend fun insertMood(mood: Mood)

    suspend fun deleteMood(mood: Mood)

    suspend fun updateMood(mood: Mood)
}
