package com.dimadyuk.newsapp.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.dimadyuk.newsapp.ui.theme.NewsAppTheme

@Composable
fun DetailScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Detail screen", fontWeight = FontWeight.SemiBold)
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    NewsAppTheme {
        DetailScreen()
    }
}
