package com.maria.shelf

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.maria.shelf.ui.theme.ShelfTheme

// Week-2: Thurs: Sep17: First Android screen
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val books = listOf(
            BookWithPages("Power of Your Subconscious Mind", totalPages = 215),
            BookWithPages("Time Management", totalPages = 128),
            BookWithPages("Never Stop Learning", totalPages = 140),
            BookWithPages("The Almanack of Naval Ravikant", totalPages = 239),
            BookWithPages("Steal Like an Artist", totalPages = 140),
            BookWithPages("Prottaborton", totalPages = 221),
            BookWithPages("The Jungle Book", totalPages = 216),
            BookWithPages("The Kite Runner", totalPages = 324),
            BookWithPages("A Thousand Splendid Suns", totalPages = 372),
            BookWithPages("Competitive Programmer's Handbook", totalPages = 285),
            BookWithPages("Paradoxical Sajid", totalPages = 160),
            BookWithPages("Paradoxical Sajid 2", totalPages = 225),
            BookWithPages("Revive Your Heart", totalPages = 160),
            BookWithPages("Satkahon", totalPages = 728),
            BookWithPages("The Miracle Morning", totalPages = 304)
        )

        setContent {
            ShelfTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ScrollableDataList(dataList = books, modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
@Composable
fun MyUI(modifier: Modifier = Modifier) {
    Column {
        Text(
            text = "Hello from Maria!!",
            modifier = modifier
        )
        Text(
            text = "Sultana",
            modifier = modifier
        )
        Button(onClick = {println("Clicked me")}) {
            Text("Click Me")
        }
    }
}

@Composable
fun ScrollableDataList(dataList: List<BookWithPages>, modifier: Modifier = Modifier) {
    LazyColumn {
        items(dataList) { item ->
            Text(
                text = "${item.title}  - ${item.totalPages} pages",
                modifier = modifier
            )
        }
    }
}