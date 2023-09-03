package com.example.moodcheck.ui.years

import androidx.lifecycle.ViewModel
import com.example.moodcheck.data.Mood

// ViewModel for Home
class YearViewModel() : ViewModel() {
    val yearUiState: YearUiState = YearUiState(
        listOf(
            Month("Jan",
                listOf(Mood(
                    day = 1,
                    month = "Jan",
                    year = 2023,
                    journal = "Sage"
                )))
        )
    )
}

// Ui State for YearScreen
data class YearUiState(
    val months: List<Month>
)

data class Month(
    val month: String,
    val moods: List<Mood>
)
