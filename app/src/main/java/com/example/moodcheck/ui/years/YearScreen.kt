package com.example.moodcheck.ui.years

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.moodcheck.MoodTopAppBar
import com.example.moodcheck.R
import com.example.moodcheck.data.Mood
import com.example.moodcheck.ui.AppViewModelProvider
import com.example.moodcheck.ui.navigation.NavigationDestination
import com.example.moodcheck.ui.theme.MoodCheckTheme
import com.example.moodcheck.ui.theme.scale_five
import com.example.moodcheck.ui.theme.scale_four
import com.example.moodcheck.ui.theme.scale_one
import com.example.moodcheck.ui.theme.scale_three
import com.example.moodcheck.ui.theme.scale_two

object YearDestination : NavigationDestination {
    override val route = "year-screen"
    override val titleRes = R.string.app_name
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun YearScreen(
    navigateToRateMood: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: YearViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val yearUiState = viewModel.yearUiState

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            MoodTopAppBar(
                title = stringResource(YearDestination.titleRes),
                scrollBehavior = scrollBehavior,
                canNavigateBack = false
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToRateMood,
                shape = MaterialTheme.shapes.extraLarge,
                modifier = Modifier.padding(20.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = stringResource(R.string.add_rating)
                )
            }
        }
    ) { innerPadding ->
        YearBody(
            monthList = yearUiState.months,
            modifier = modifier.padding(innerPadding)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun YearScreenPreview() {
    MoodCheckTheme {
        YearScreen({})
    }
}

@Composable // Rows of columns
private fun YearBody(
    monthList: List<Month>,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(top = 16.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(1.dp)
        ) {
            Text(text = "")
            for (day in 1..31) {
                MoodBubble(
                    day = day,
                    mood = Mood()
                )
            }
        }
        monthList.forEach { month ->
            MoodList(
                month = month.month,
                moodList = month.moods,
                modifier = Modifier.padding(1.dp)
            )
        }
    }
}

@Composable
private fun MoodList(
    month: String,
    moodList: List<Mood>,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(text = month)
        moodList.forEach { mood ->
            MoodBubble(mood = mood)
        }
    }
}

@Composable
fun MoodBubble(
    mood: Mood,
    modifier: Modifier = Modifier,
    day: Int = 0,
) {
    val color = when(mood.rating) {
        1 -> scale_one
        2 -> scale_two
        3 -> scale_three
        4 -> scale_four
        5 -> scale_five
        else -> MaterialTheme.colorScheme.background
    }
    val outlineColor = if (day == 0) {
        MaterialTheme.colorScheme.outline
    } else { MaterialTheme.colorScheme.background }

    Surface(
        shape = MaterialTheme.shapes.large,
        color = color,
        modifier = modifier
            .height(32.dp)
            .width(32.dp)
            .padding(1.dp)
//            .border(
//                width = 0.5.dp,
//                color = outlineColor,
//                shape = MaterialTheme.shapes.extraSmall
//            )
    ) {
        // Wrap text in box to align contents
        Box(modifier = modifier, contentAlignment = Alignment.Center) {
            Text(
                text = if (day == 0) { "" } else { "$day" },
            )
        }
    }
}

