package com.example.moodcheck.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.moodcheck.MoodApplication
import com.example.moodcheck.ui.rate.PastMoodViewModel
import com.example.moodcheck.ui.rate.RateMoodViewModel
import com.example.moodcheck.ui.years.YearViewModel

// Provides Factory to create ViewModel instances
object AppViewModelProvider {
    val Factory = viewModelFactory {
        // initializer for YearViewModel
        initializer {
            YearViewModel(
                moodApplication().container.moodsRepository,
                moodApplication().userPreferencesRepository
            )
        }

        // initializer for RateMoodViewModel
        initializer {
            RateMoodViewModel(moodApplication().container.moodsRepository)
        }

        // initializer for PastMoodViewModel
        initializer {
            PastMoodViewModel(
                this.createSavedStateHandle(),
                moodApplication().container.moodsRepository
            )
        }
    }
}

fun CreationExtras.moodApplication(): MoodApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MoodApplication)
