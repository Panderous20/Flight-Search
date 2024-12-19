package com.example.flightsearchv2.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.flightsearchv2.data.MockData
import com.example.flightsearchv2.model.Airport
import com.example.flightsearchv2.ui.theme.FlightSearchV2Theme

@Composable
fun SuggestScreen(
    suggestList: List<Airport> = emptyList(),
    onSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth()
            .padding(8.dp)
    ) {
        items(suggestList) {
            SuggestCard(
                code = it.code,
                name = it.name,
                onSelected = onSelected,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp)
            )
        }
    }
}

@Composable
fun SuggestCard(
    code: String,
    name: String,
    onSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = CardDefaults.cardElevation(1.dp),
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp) // Tăng chiều cao để tạo không gian
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    if (code.isNotEmpty()) {
                        onSelected(code)
                    }
                }
                .padding(horizontal = 8.dp), // Thêm padding ngang
            // Căn giữa theo chiều dọc
        ) {
            Text(
                text = code,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 4.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = name,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CardPreview() {
    FlightSearchV2Theme {
        SuggestCard(
            "abc",
            "vietnam airline",
            {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SuggestScreenPreview() {
    FlightSearchV2Theme {
        SuggestScreen(
            suggestList = MockData.airports,
            onSelected = {}
        )
    }
}