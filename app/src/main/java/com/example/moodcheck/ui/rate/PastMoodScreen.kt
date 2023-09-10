package com.example.moodcheck.ui.rate

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.moodcheck.MoodTopAppBar
import com.example.moodcheck.R
import com.example.moodcheck.data.Mood
import com.example.moodcheck.ui.AppViewModelProvider
import com.example.moodcheck.ui.navigation.NavigationDestination
import com.example.moodcheck.ui.theme.MoodCheckTheme
import com.example.moodcheck.ui.years.MoodBubble

/**
 * Screen that comes up from clicking on a past mood
 * Displays date, rating, and journal from that day
 */

object PastDestination : NavigationDestination {
    override val route = "past"
    override val titleRes = R.string.rating_for
    const val moodIdArg = "moodId"
    val routeWithArgs = "$route/{$moodIdArg}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PastMoodScreen(
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: PastMoodViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            MoodTopAppBar(
                title = stringResource(
                    PastDestination.titleRes, uiState.mood.month, uiState.mood.day, uiState.mood.year
                ),
                canNavigateBack = true,
                navigateUp = onNavigateUp
            )
        },

    ) { innerPadding ->
        PastMoodBody(
            mood = uiState.mood,
            modifier = modifier.padding(innerPadding)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PastMoodScreenPreview() {
    MoodCheckTheme {
        PastMoodScreen(
            onNavigateUp = { /*TODO*/ }
        )
    }
}

@Composable
fun PastMoodBody(
    mood: Mood,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.padding(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Text("Rating:")
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "${mood.rating}",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                MoodBubble(mood = Mood(rating = mood.rating), navigateToPastMood = {})
            }
            OutlinedTextField(
                value = mood.journal,
                onValueChange = { },
                label = { Text(text = stringResource(R.string.journal_entry)) },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.background,
                    unfocusedContainerColor = MaterialTheme.colorScheme.background,
                    disabledContainerColor = MaterialTheme.colorScheme.background
                ),
                modifier = Modifier.fillMaxWidth(),
                enabled = false,
                minLines = 5,
            )
        }
    }
}
