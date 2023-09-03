package com.example.moodcheck.ui.rate

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.moodcheck.MoodTopAppBar
import com.example.moodcheck.R
import com.example.moodcheck.ui.AppViewModelProvider
import com.example.moodcheck.ui.navigation.NavigationDestination
import com.example.moodcheck.ui.theme.MoodCheckTheme

object RateDestination : NavigationDestination {
    override val route = "rate"
    override val titleRes = R.string.rate_your_day
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RateMoodScreen(
    modifier: Modifier = Modifier,
    viewModel: RateMoodViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    Scaffold(
        topBar = {
            MoodTopAppBar(
                title = stringResource(RateDestination.titleRes),
                modifier = modifier
            )
        }
    ) { innerPadding ->
        RateMoodBody(
            rateUiState = viewModel.rateUiState,
            onSliderChange = viewModel::updateUiState,
            modifier = modifier.padding(innerPadding)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RateMoodScreenPreview() {
    MoodCheckTheme {
        RateMoodScreen()
    }
}

@Composable
fun RateMoodBody(
    rateUiState: RateUiState,
    onSliderChange: (MoodDetails) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(8.dp)) {
        Text(
            text = stringResource(R.string.rate_feeling_prompt, rateUiState.mood.rating)
        )
        RateMoodSlider(
            moodDetails = rateUiState.mood,
            onSliderChange = onSliderChange
        )
        RateMoodJournal()
        Button(
            onClick = {},
            enabled = true,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.save_action))
        }
    }
}

@Composable
fun RateMoodSlider(
    moodDetails: MoodDetails,
    onSliderChange: (MoodDetails) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Slider(
            value = moodDetails.rating.toFloat(),
            onValueChange = { onSliderChange(moodDetails.copy(rating = it.toInt())) },
            colors = SliderDefaults.colors(
                thumbColor = MaterialTheme.colorScheme.secondary,
                activeTrackColor = MaterialTheme.colorScheme.secondary,
                inactiveTrackColor = MaterialTheme.colorScheme.secondaryContainer
            ),
            steps = 3,
            valueRange = 1f..5f
        )
//        Text(text = moodDetails.rating.toString())
    }
}

@Composable
fun RateMoodJournal(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        OutlinedTextField(
            value = stringResource(R.string.journal_about_your_feelings),
            onValueChange = { /* TODO */ },
            label = { Text(text = stringResource(R.string.journal_entry)) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer
            ),
            modifier = Modifier.fillMaxWidth(),
            enabled = true,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RateMoodJournalPreview() {
    MoodCheckTheme {
        RateMoodJournal()
    }
}
