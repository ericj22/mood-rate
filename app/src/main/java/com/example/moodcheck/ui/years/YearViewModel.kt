package com.example.moodcheck.ui.years

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moodcheck.data.Mood
import com.example.moodcheck.data.MoodsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

// ViewModel for Home
class YearViewModel(moodsRepository: MoodsRepository) : ViewModel() {
    val yearUiState: StateFlow<YearUiState> =
        moodsRepository.getMonths(2023).map { YearUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = YearUiState()
            )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

// Ui State for YearScreen
data class YearUiState(
    val months: Map<String, List<Mood>> = mapOf()
)
