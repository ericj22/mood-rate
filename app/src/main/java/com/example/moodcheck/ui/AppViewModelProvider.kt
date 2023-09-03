package com.example.moodcheck.ui

import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.moodcheck.ui.rate.RateMoodViewModel
import com.example.moodcheck.ui.years.YearViewModel

// Provides Factory to create ViewModel instances
object AppViewModelProvider {
    val Factory = viewModelFactory {
        // initializer for YearViewModel
        initializer {
            YearViewModel()
        }

        // initializer for RateMoodViewModel
        initializer {
            RateMoodViewModel()
        }
    }
}
