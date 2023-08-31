package com.example.moodcheck.ui.years

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moodcheck.MoodTopAppBar
import com.example.moodcheck.ui.theme.MoodCheckTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun YearScreen(
    modifier: Modifier = Modifier
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            MoodTopAppBar(
                title = "Title",
                scrollBehavior = scrollBehavior
            )
        }
    ) { innerPadding ->
        Text(
            text = "Hi",
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable // Rows of columns
private fun YearBody(
    monthList: List<String>,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
    ) {
        for (month in monthList) {
            Text(text = month)
        }
    }
}

@Preview
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
        modifier = modifier
    ) {
        Text(text = month)
        for (mood in moodList) {
            Surface(
                shape = MaterialTheme.shapes.medium,
                color = MaterialTheme.colorScheme.tertiaryContainer,
                modifier = modifier
                    .height(32.dp)
                    .width(32.dp)
                    .padding(4.dp)
            ) {
                Text(
                    text = "$mood",
                )
            }
        }
    }
}

@Preview
@Composable
private fun MoodListPreview() {
    MoodCheckTheme {
        MoodList(
            month = "JAN",
            moodList = listOf(1, 2, 3, 4, 5)
        )
    }
}
