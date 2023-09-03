package com.example.moodcheck.ui.rate

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.moodcheck.data.Mood

class RateMoodViewModel() : ViewModel() {
    // Holds current Rating ui state
    var rateUiState by mutableStateOf(RateUiState())
        private set

    fun updateUiState(moodDetails: MoodDetails) {
        rateUiState = RateUiState(mood = moodDetails)
    }
}

data class RateUiState(
    val mood: MoodDetails = MoodDetails()
)

data class MoodDetails(
    val id: Int = 0,
    val rating: Int = 3,
    val journal: String = ""
)
