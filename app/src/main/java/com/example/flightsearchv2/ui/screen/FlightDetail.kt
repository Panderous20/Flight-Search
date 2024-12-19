package com.example.flightsearchv2.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.flightsearchv2.data.MockData

@Composable
fun FlightDetail(
    departureCode: String,
    departureName: String,
    destinationCode: String,
    destinationName: String,
    isFavorite: Boolean = false,
    onFavoriteClick: (String, String) -> Unit,
    modifier: Modifier = Modifier
){
    Card(
        modifier = modifier.fillMaxWidth()
            .padding(horizontal = 8.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        shape = RectangleShape
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
        ) {
            Column(
                modifier = Modifier.weight(10f)
                    .padding(vertical = 4.dp)
            ) {
                Text(
                    text = "Depart",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(start = 32.dp)
                )
                DetailItem(departureCode, departureName)
                Text(
                    text = "Arrive",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(start = 32.dp)
                )
                DetailItem(destinationCode, destinationName)
            }
            IconButton(
                enabled = true,
                onClick ={
                    onFavoriteClick(departureCode, destinationCode)
                         },
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Icon(
                    imageVector = if(isFavorite)Icons.Filled.Star else Icons.Outlined.Star,
                    contentDescription = null,
                    tint = if(isFavorite) Color.Yellow else Color.LightGray

                )
            }
        }
    }
}
@Composable
fun DetailItem(
    code: String,
    name: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .padding(start = 8.dp)
    ) {
        Text(
            text = code,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = name,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}


@Preview
@Composable
fun FlightRowPreview() {
    val mock = MockData
    FlightDetail(
        isFavorite = false,
        departureCode = mock.airports[1].code,
        departureName = mock.airports[1].name,
        destinationCode = mock.airports[2].code,
        destinationName = mock.airports[2].name,
        onFavoriteClick = { _: String, _:String ->}
    )
}

