package com.example.moodcheck.ui.rate

import android.icu.util.Calendar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moodcheck.data.MONTHS
import com.example.moodcheck.data.Mood
import com.example.moodcheck.data.MoodsRepository
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class RateMoodViewModel(
    private val moodsRepository: MoodsRepository
) : ViewModel() {
    // Holds current Rating ui state
    var rateUiState by mutableStateOf(RateUiState())
        private set

    private val calendar: Calendar = Calendar.getInstance()
    private val month: String = MONTHS[calendar.get(Calendar.MONTH)]
    private val year: Int  = calendar.get(Calendar.YEAR)
    private val day: Int = calendar.get(Calendar.DAY_OF_MONTH)

    init {
        viewModelScope.launch {
            rateUiState = moodsRepository.getMoodByDate(year, month, day)
                .filterNotNull()
                .first()
                .toRateUiState()
        }
    }

    fun updateUiState(moodDetails: MoodDetails) {
        rateUiState = RateUiState(mood = moodDetails)
    }

    suspend fun updateMood() {
        moodsRepository.updateMood(
            Mood(
                id = rateUiState.mood.id,
                rating = rateUiState.mood.rating,
                year = year,
                month = month,
                day = day,
                journal = rateUiState.mood.journal
            )
        )
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

fun Mood.toRateUiState(): RateUiState = RateUiState(
    mood = MoodDetails(this.id, this.rating, this.journal)
)
