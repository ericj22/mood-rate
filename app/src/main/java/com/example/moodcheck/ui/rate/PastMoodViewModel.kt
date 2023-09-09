package com.example.moodcheck.ui.rate

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moodcheck.data.Mood
import com.example.moodcheck.data.MoodsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class PastMoodViewModel(
    savedStateHandle: SavedStateHandle,
    private val moodsRepository: MoodsRepository
) : ViewModel() {
    private val moodId: Int = checkNotNull(savedStateHandle[PastDestination.moodIdArg])

    val uiState: StateFlow<PastMoodUiState> = moodsRepository.getMood(moodId)
        .filterNotNull()
        .map { PastMoodUiState(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = PastMoodUiState()
        )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class PastMoodUiState(
    val mood: Mood = Mood()
)
