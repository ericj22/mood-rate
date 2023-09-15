package com.example.moodcheck.ui.help

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moodcheck.MoodTopAppBar
import com.example.moodcheck.R
import com.example.moodcheck.data.Mood
import com.example.moodcheck.ui.navigation.NavigationDestination
import com.example.moodcheck.ui.theme.MoodCheckTheme
import com.example.moodcheck.ui.years.MoodBubble

object HelpDestination : NavigationDestination {
    override val route = "help-screen"
    override val titleRes = R.string.help
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HelpScreen(
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            MoodTopAppBar(
                title = stringResource(HelpDestination.titleRes),
                canNavigateBack = true,
                navigateUp = onNavigateUp,
                canOpenHelp = false
            )
        }
    ) { innerPadding ->
        HelpBody(modifier.padding(innerPadding))
    }
}

@Preview(showBackground = true)
@Composable
fun HelpScreenPreview() {
    MoodCheckTheme {
        HelpScreen({})
    }
}

@Composable
fun HelpBody(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(32.dp)
    ){
        Text("Welcome to Rate my Day!")
        Spacer(Modifier.padding(vertical = 8.dp))
        Text(stringResource(R.string.help_instr_one))
        Text(stringResource(R.string.help_instr_two))
        Legend(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp)
        )
    }
}

@Composable
fun Legend(
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier
    ) {
        for (i in 1..5 step 2) {
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                LegendItem(rating = i, value = "$i")
                LegendItem(
                    rating = i+1,
                    value = if (i != 5) {
                        "${i+1}"
                    } else {
                        stringResource(R.string.n_a)
                    }
                )
            }
        }
    }
}

@Composable
fun LegendItem(
    rating: Int,
    value: String,
) {
    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        MoodBubble(mood = Mood(rating = rating), navigateToPastMood = {}, border = rating == 6)
        Text("  =  $value")
    }
}
