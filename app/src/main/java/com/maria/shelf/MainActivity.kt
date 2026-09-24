package com.maria.shelf

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
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
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import com.maria.shelf.ui.theme.ShelfTheme

data class BookWithPage(
    val id: Long,
    val title: String,
    val author: String,
    val status: ReadingStatus,
    val totalPages: Int
)

// Week-2: Thurs: Sep17: First Android screen
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val books = listOf(
            BookWithPage(
                id = 1L,
                title = "The Power of Your Subconscious Mind",
                author = "Joseph Murphy",
                status = ReadingStatus.FINISHED,
                totalPages = 215
            ),
            BookWithPage(
                id = 2L,
                title = "Time Management",
                author = "Brian Tracy",
                status = ReadingStatus.READING,
                totalPages = 128
            ),
            BookWithPage(
                id = 3L,
                title = "Never Stop Learning",
                author = "Bradley R. Staats",
                status = ReadingStatus.WANT_TO_READ,
                totalPages = 140
            ),
            BookWithPage(
                id = 4L,
                title = "The Almanack of Naval Ravikant",
                author = "Eric Jorgenson",
                status = ReadingStatus.READING,
                totalPages = 239
            ),
            BookWithPage(
                id = 5L,
                title = "Steal Like an Artist",
                author = "Austin Kleon",
                status = ReadingStatus.FINISHED,
                totalPages = 160
            ),
            BookWithPage(
                id = 6L,
                title = "Prottaborton",
                author = "Humayun Ahmed",
                status = ReadingStatus.WANT_TO_READ,
                totalPages = 221
            ),
            BookWithPage(
                id = 7L,
                title = "The Jungle Book",
                author = "Rudyard Kipling",
                status = ReadingStatus.FINISHED,
                totalPages = 216
            ),
            BookWithPage(
                id = 8L,
                title = "The Kite Runner",
                author = "Khaled Hosseini",
                status = ReadingStatus.FINISHED,
                totalPages = 324
            ),
            BookWithPage(
                id = 9L,
                title = "A Thousand Splendid Suns",
                author = "Khaled Hosseini",
                status = ReadingStatus.WANT_TO_READ,
                totalPages = 372
            ),
            BookWithPage(
                id = 10L,
                title = "Competitive Programmer's Handbook",
                author = "Antti Laaksonen",
                status = ReadingStatus.READING,
                totalPages = 285
            ),
            BookWithPage(
                id = 11L,
                title = "Paradoxical Sajid",
                author = "Arif Azad",
                status = ReadingStatus.FINISHED,
                totalPages = 160
            ),
            BookWithPage(
                id = 12L,
                title = "Paradoxical Sajid 2",
                author = "Arif Azad",
                status = ReadingStatus.WANT_TO_READ,
                totalPages = 225
            ),
            BookWithPage(
                id = 13L,
                title = "Revive Your Heart",
                author = "Nouman Ali Khan",
                status = ReadingStatus.READING,
                totalPages = 160
            ),
            BookWithPage(
                id = 14L,
                title = "Satkahon",
                author = "Suchitra Bhattacharya",
                status = ReadingStatus.FINISHED,
                totalPages = 728
            ),
            BookWithPage(
                id = 15L,
                title = "The Miracle Morning",
                author = "Hal Elrod",
                status = ReadingStatus.WANT_TO_READ,
                totalPages = 304
            )
        )
        setContent {
            ShelfTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ScrollableDataList(
                        bookList = books,
                        modifier = Modifier
                            .padding(innerPadding)
                    )
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScrollableDataList(bookList: List<BookWithPage>, modifier: Modifier = Modifier) {
    var query by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }

    val filteredBooks = bookList.filter {
        it.title.contains(query, ignoreCase = true)
    }

    SearchBar(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        shape = RoundedCornerShape(20.dp),
        query = query,
        onQueryChange = { query = it },
        onSearch = { active = false},
        active = active,
        onActiveChange = { active = it },
        placeholder = { Text("Search Books") },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search Icon")}
    ) {
        LazyColumn (
            modifier = modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            items(
                items = filteredBooks,
                key = { book -> book.id }
            ) { book ->
                BookRow(book) {
                    println("Clicked a book row with id: ${book.id}")
                }
            }
        }
    }
}

@Composable
fun BookRow(
    book: BookWithPage,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.LightGray)
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = book.title,
                color = Color.Black
            )
            Text(
                text = "${book.totalPages} pages",
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
                text = "by ${book.author}",
                color = Color.Black
            )
            Text(
                text = book.status.toString(),
                color = Color.Black
            )
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