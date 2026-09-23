package com.maria.shelf

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.maria.shelf.ui.theme.ShelfTheme

data class BookWithPage(
    val title: String,
    val author: String,
    val status: String,
    val totalPages: Int
)

// Week-2: Thurs: Sep17: First Android screen
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val books = listOf(
            BookWithPage(
                title = "The Power of Your Subconscious Mind",
                author = "Joseph Murphy",
                status = "Not Started",
                totalPages = 215
            ),
            BookWithPage(
                title = "Time Management",
                author = "Brian Tracy",
                status = "Not Started",
                totalPages = 128
            ),
            BookWithPage(
                title = "Never Stop Learning",
                author = "Bradley R. Staats",
                status = "Not Started",
                totalPages = 140
            ),
            BookWithPage(
                title = "The Almanack of Naval Ravikant",
                author = "Eric Jorgenson",
                status = "Not Started",
                totalPages = 239
            ),
            BookWithPage(
                title = "Steal Like an Artist",
                author = "Austin Kleon",
                status = "Not Started",
                totalPages = 160
            ),
            BookWithPage(
                title = "Prottaborton",
                author = "Humayun Ahmed",
                status = "Not Started",
                totalPages = 221
            ),
            BookWithPage(
                title = "The Jungle Book",
                author = "Rudyard Kipling",
                status = "Not Started",
                totalPages = 216
            ),
            BookWithPage(
                title = "The Kite Runner",
                author = "Khaled Hosseini",
                status = "Not Started",
                totalPages = 324
            ),
            BookWithPage(
                title = "A Thousand Splendid Suns",
                author = "Khaled Hosseini",
                status = "Not Started",
                totalPages = 372
            ),
            BookWithPage(
                title = "Competitive Programmer's Handbook",
                author = "Antti Laaksonen",
                status = "Not Started",
                totalPages = 285
            ),
            BookWithPage(
                title = "Paradoxical Sajid",
                author = "Arif Azad",
                status = "Not Started",
                totalPages = 160
            ),
            BookWithPage(
                title = "Paradoxical Sajid 2",
                author = "Arif Azad",
                status = "Not Started",
                totalPages = 225
            ),
            BookWithPage(
                title = "Revive Your Heart",
                author = "Nouman Ali Khan",
                status = "Not Started",
                totalPages = 160
            ),
            BookWithPage(
                title = "Satkahon",
                author = "Suchitra Bhattacharya",
                status = "Not Started",
                totalPages = 728
            ),
            BookWithPage(
                title = "The Miracle Morning",
                author = "Hal Elrod",
                status = "Not Started",
                totalPages = 304
            )
        )
        setContent {
            ShelfTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    FakeComposable(modifier = Modifier.padding(innerPadding))
//                    ScrollableDataList(
//                        dataList = books,
//                        modifier = Modifier
//                            .padding(innerPadding)
//                    )
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
fun ScrollableDataList(dataList: List<BookWithPage>, modifier: Modifier = Modifier) {
    LazyColumn (
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        items(dataList) { item ->
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.LightGray)
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = item.title,
                    color = Color.Black
                )
                Text(
                    text = "${item.totalPages} pages",
                    color = Color.Black
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.LightGray)
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "by ${item.author}",
                    color = Color.Black
                )
                Text(
                    text = item.status,
                    color = Color.Black
                )
            }
        }
    }
}

@Composable
fun Counter(modifier: Modifier = Modifier, counter: Int, onClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(counter.toString())
        Spacer(Modifier.height(10.dp))
        Button(onClick = onClick) {
            Text("Click Me")
        }
    }
}

@Composable
fun FakeComposable(modifier: Modifier) {
    var counter by remember { mutableStateOf(0) }

    Counter(
        modifier = modifier,
        counter = counter,
        onClick = {
            counter++
        }
    )
}