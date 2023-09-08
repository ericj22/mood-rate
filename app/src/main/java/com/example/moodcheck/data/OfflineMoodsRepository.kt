package com.example.moodcheck.data

import kotlinx.coroutines.flow.Flow

class OfflineMoodsRepository(private val moodDao: MoodDao) : MoodsRepository {
    override fun getMonthStream(month: String): Flow<List<Mood>> = moodDao.getMonth(month)

    override fun getMood(id: Int): Flow<Mood?> = moodDao.getMood(id)

    override suspend fun insertMood(mood: Mood) = moodDao.insert(mood)

    override suspend fun deleteMood(mood: Mood) = moodDao.delete(mood)

    override suspend fun updateMood(mood: Mood) = moodDao.update(mood)
}
