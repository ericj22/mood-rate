package com.example.moodcheck.ui.years

import androidx.lifecycle.ViewModel

// ViewModel for Home
class YearViewModel() : ViewModel() {
    // Need to set up stuff to retrieve items from Room database
}

// Ui State for YearScreen
data class YearUiState(val itemList: List<String> = listOf())
