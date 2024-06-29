package com.dimadyuk.newsapp.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SearchBar(
    query: State<String>,
    onSearchClick: (String) -> Unit = {},
    onSearchChanged: (String) -> Unit = {},
) {
    val localFocusManager = LocalFocusManager.current
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        shape = RoundedCornerShape(6.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        TextField(
            value = query.value,
            textStyle = TextStyle(
                color = Color.White,
                fontSize = 18.sp
            ),
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                unfocusedContainerColor = MaterialTheme.colorScheme.primary,
                focusedContainerColor = MaterialTheme.colorScheme.primary,
            ),
            onValueChange = {
                onSearchChanged(it)
            },
            modifier = Modifier
                .fillMaxWidth(),
            label = {
                Text(
                    text = "Search",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.White
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    if (query.value.isNotBlank()) {
                        onSearchClick(query.value)
                        localFocusManager.clearFocus()
                    }
                }
            ),
            leadingIcon = {
                IconButton(onClick = {
                    if (query.value.isNotBlank()) {
                        onSearchClick(query.value)
                        localFocusManager.clearFocus()
                    }
                }) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        tint = Color.White,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(8.dp)
                    )
                }
            },
            trailingIcon = {
                if (query.value.isNotBlank()) {
                    IconButton(onClick = {
                        onSearchChanged("")
                    }) {
                        Icon(
                            imageVector = Icons.Outlined.Clear,
                            tint = Color.White,
                            contentDescription = null,
                            modifier = Modifier
                                .padding(8.dp)
                        )
                    }
                }
            }
        )

    }
}

@SuppressLint("UnrememberedMutableState")
@Preview
@Composable
fun SearchBarPreview(modifier: Modifier = Modifier) {

    SearchBar(
        query = mutableStateOf("dfbcfb"),
        onSearchClick = {

        },
    )
}