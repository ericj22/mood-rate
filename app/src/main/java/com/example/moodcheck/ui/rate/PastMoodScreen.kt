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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moodcheck.MoodTopAppBar
import com.example.moodcheck.R
import com.example.moodcheck.data.Mood
import com.example.moodcheck.ui.theme.MoodCheckTheme
import com.example.moodcheck.ui.years.MoodBubble

/**
 * Screen that comes up from clicking on a past mood
 * Displays date, rating, and journal from that day
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PastMoodScreen(
    onNavigateUp: () -> Unit,
    mood: Mood,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            MoodTopAppBar(
                title = "/Date/ Mood",
                canNavigateBack = true,
                navigateUp = onNavigateUp
            )
        },

    ) { innerPadding ->
        PastMoodBody(
            mood = mood,
            modifier = modifier.padding(innerPadding)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PastMoodScreenPreview() {
    MoodCheckTheme {
        PastMoodScreen(
            onNavigateUp = { /*TODO*/ },
            Mood(rating = 3, journal = "cry today I cried a lot and I was really sad but that means I'm a little suicidal so I rated the day a 3 since I didn't cut myself but I was also pretty sad and nothing went particularly well. I'm really sadge now but oh well idk what to do ")
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
                MoodBubble(mood = Mood(rating = mood.rating))
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