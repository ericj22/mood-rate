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
import com.example.moodcheck.MoodTopAppBar
import com.example.moodcheck.R
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
    modifier: Modifier = Modifier
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

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
            monthList = listOf(
                "Jan", "Feb", "Mar", "Apr"
            ),
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
    monthList: List<String>,
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
                    mood = 0
                )
            }
        }
        monthList.forEach { month ->
            MoodList(
                month = month,
                moodList = listOf(
                    1, 2, 3, 4, 5, 4, 5, 3, 3, 1, 1, 1, 4, 5, 4, 3, 2, 1, 2, 5, 3, 4, 1, 0, 0, 0, 0, 0, 0, 0
                ),
                modifier = Modifier.padding(1.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun YearBodyPreview() {
    MoodCheckTheme {
        YearBody(
            listOf(
                "JAN",
                "FEB",
                "MAR",
                "APR"
            )
        )
    }
}

@Composable
private fun MoodList(
    month: String,
    moodList: List<Int>,
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

@Preview(showBackground = true)
@Composable
private fun MoodListPreview() {
    MoodCheckTheme {
        MoodList(
            month = stringResource(R.string.jan),
            moodList = listOf(1, 2, 3, 4, 5)
        )
    }
}

@Composable
private fun MoodBubble(
    mood: Int,
    modifier: Modifier = Modifier,
    day: Int = 0,
) {
    val color = when(mood) {
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
//        shape = MaterialTheme.shapes.extraSmall,
        color = color,
        modifier = modifier
            .height(32.dp)
            .width(32.dp)
            .padding(1.dp)
            .border(
                width = 0.5.dp,
                color = outlineColor,
//                shape = MaterialTheme.shapes.extraSmall
            )
    ) {
        // Wrap text in box to align contents
        Box(modifier = modifier, contentAlignment = Alignment.Center) {
            Text(
                text = if (day == 0) { "" } else { "$day" },
            )
        }
    }
}

