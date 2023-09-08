package com.example.moodcheck.data

import android.content.Context

/**
 * App container for Dependency injection
 */

interface AppContainer {
    val moodsRepository: MoodsRepository
}

/**
 * [AppContainer] implementation that provides instance of [OfflineMoodsRepository]
 */
class AppDataContainer(private val context: Context) : AppContainer {
    /**
     * Implementation for [MoodsRepository]
     */
    override val moodsRepository: MoodsRepository by lazy {
        OfflineMoodsRepository(MoodDatabase.getDatabase(context).moodDao())
    }
}
