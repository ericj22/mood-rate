package com.example.moodcheck.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.MapInfo
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface MoodDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(mood: Mood)

    @Update
    suspend fun update(mood: Mood)

    @Delete
    suspend fun delete(mood: Mood)

    @Query("SELECT * from moods WHERE id = :id")
    fun getMood(id: Int): Flow<Mood>

    @MapInfo(keyColumn = "month")
    @Query(
        "SELECT * from moods WHERE year = :year ORDER BY day ASC"
    )
    fun getMonths(year: Int): Flow<Map<String, List<Mood>>>
}
