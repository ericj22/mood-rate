package com.example.moodcheck

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.moodcheck.data.AppContainer
import com.example.moodcheck.data.AppDataContainer
import com.example.moodcheck.data.UserPreferencesRepository

private const val LAST_YEAR_POPULATED = "last_year_populated"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = LAST_YEAR_POPULATED
)

class MoodApplication : Application() {
    lateinit var container: AppContainer
    lateinit var userPreferencesRepository: UserPreferencesRepository

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
        userPreferencesRepository = UserPreferencesRepository(dataStore)
    }
}
