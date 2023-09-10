package com.example.moodcheck.ui.years

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moodcheck.data.DAYS_IN_MONTH
import com.example.moodcheck.data.MONTHS
import com.example.moodcheck.data.Mood
import com.example.moodcheck.data.MoodsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.Calendar

// ViewModel for Home
class YearViewModel(
    private val moodsRepository: MoodsRepository
) : ViewModel() {
    private val year: Int = Calendar.getInstance().get(Calendar.YEAR)

    val yearUiState: StateFlow<YearUiState> =
        moodsRepository.getMonths(year).map { YearUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = YearUiState()
            )

    init {
        if (yearUiState.value.months.isEmpty()) {
            MONTHS.forEach { month ->
                for (day in 1..DAYS_IN_MONTH[month]!!) {
                    viewModelScope.launch {
                        moodsRepository.insertMood(
                            Mood(
                                rating = 0,
                                year = year,
                                month = month,
                                day = day
                            )
                        )
                    }
                }
            }
        }
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

// Ui State for YearScreen
data class YearUiState(
    val months: Map<String, List<Mood>> = mapOf()
)
