package com.example.moodcheck.data

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class UserPreferencesRepository(
    private val dataStore: DataStore<Preferences>
) {
    val lastYearPopulated: Flow<Int> = dataStore.data
        .catch {
            if (it is IOException) {
                Log.e(TAG, "Error reading preferences.", it)
            } else {
                throw it
            }
        }
        .map { preferences ->
            preferences[LAST_YEAR_POPULATED] ?: 0
        }

    private companion object {
        val LAST_YEAR_POPULATED = intPreferencesKey("last_year_populated")
        const val TAG = "UserPreferencesRepo"
    }

    suspend fun saveYearPopulated(lastYearPopulated: Int) {
        dataStore.edit { preferences ->
            preferences[LAST_YEAR_POPULATED] = lastYearPopulated
        }
    }
}
