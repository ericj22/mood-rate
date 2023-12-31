package com.example.moodcheck.data

import kotlinx.coroutines.flow.Flow

class OfflineMoodsRepository(private val moodDao: MoodDao) : MoodsRepository {
    override fun getMonths(year: Int): Flow<Map<String, List<Mood>>> = moodDao.getMonths(year)

    override fun getMoodByDate(year: Int, month: String, day: Int): Flow<Mood?> =
        moodDao.getMoodByDate(year, month, day)

    override fun getMood(id: Int): Flow<Mood?> = moodDao.getMood(id)

    override suspend fun insertMood(mood: Mood) = moodDao.insert(mood)

    override suspend fun deleteMood(mood: Mood) = moodDao.delete(mood)

    override suspend fun updateMood(mood: Mood) = moodDao.update(mood)
}
