package com.example.flightsearchv2.ui.screen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.flightsearchv2.R
import com.example.flightsearchv2.ui.theme.FlightSearchV2Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchField(
    query: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = query,
        onValueChange = { onValueChange(it)},
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = null,
            )
        },
        trailingIcon = {
            Icon(
                painterResource(R.drawable.mic_186x256),
                contentDescription = "Mic Icon",
                modifier = Modifier.size(20.dp)
            )
        },
        label = { Text(stringResource(R.string.search_airport)) },
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
            .height(60.dp),
        shape = RoundedCornerShape(36.dp),
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )

    )
}

@Preview
@Composable
fun Preview() {
    FlightSearchV2Theme {
        SearchField("", {})
    }
}