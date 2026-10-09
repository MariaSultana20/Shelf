package com.maria.shelf

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import com.maria.shelf.ui.theme.ShelfTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.ui.Modifier
import  androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.MutableState
import androidx.compose.ui.text.input.KeyboardType
data class Book(
    val id: Long,
    val title: String,
    val author: String,
    val status: ReadingStatus,
    val totalPages: Int,
    val pagesRead: Int,
    val rating: Int?,
    val notes: String?,
    val dateAdded: Long,
    val dateFinished: Long?
)
val shape = RoundedCornerShape(4.dp)

val now = System.currentTimeMillis()
val books = mutableListOf<Book>(
    Book(1L, "The Power of Your Subconscious Mind", "Joseph Murphy", ReadingStatus.FINISHED, 215, 215, null, null, now, null),
    Book(2L, "Time Management", "Brian Tracy", ReadingStatus.READING, 128, 0, null, null, now, null),
    Book(3L, "Never Stop Learning", "Bradley R. Staats", ReadingStatus.WANT_TO_READ, 140, 0, null, null, now, null),
    Book(4L, "The Almanack of Naval Ravikant", "Eric Jorgenson", ReadingStatus.READING, 239, 0, null, null, now, null),
    Book(5L, "Steal Like an Artist", "Austin Kleon", ReadingStatus.FINISHED, 160, 160, null, null, now, null),
    Book(6L, "Prottaborton", "Humayun Ahmed", ReadingStatus.WANT_TO_READ, 221, 0, null, null, now, null),
    Book(7L, "The Jungle Book", "Rudyard Kipling", ReadingStatus.FINISHED, 216, 216, null, null, now, null),
    Book(8L, "The Kite Runner", "Khaled Hosseini", ReadingStatus.FINISHED, 324, 324, null, null, now, null),
    Book(9L, "A Thousand Splendid Suns", "Khaled Hosseini", ReadingStatus.WANT_TO_READ, 372, 0, null, null, now, null),
    Book(10L, "Competitive Programmer's Handbook", "Antti Laaksonen", ReadingStatus.READING, 285, 0, null, null, now, null),
    Book(11L, "Paradoxical Sajid", "Arif Azad", ReadingStatus.FINISHED, 160, 160, null, null, now, null),
    Book(12L, "Paradoxical Sajid 2", "Arif Azad", ReadingStatus.WANT_TO_READ, 225, 0, null, null, now, null),
    Book(13L, "Revive Your Heart", "Nouman Ali Khan", ReadingStatus.READING, 160, 0, null, null, now, null),
    Book(14L, "Satkahon", "Suchitra Bhattacharya", ReadingStatus.FINISHED, 728, 728, null, null, now, null),
    Book(15L, "The Miracle Morning", "Hal Elrod", ReadingStatus.WANT_TO_READ, 304, 0, null, null, now, null)
)
// Week-2: Thurs: Sep17: First Android screen
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ShelfTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    ScrollableDataList(
//                        bookList = books,
//                        modifier = Modifier
//                            .padding(innerPadding)
//                    )
                    //MyTextField(modifier = Modifier.padding(innerPadding))
//                    RegistrationForm()
                    AddBookForm()
                }
            }
        }
    }
}
@Composable
fun MyTextField(modifier: Modifier = Modifier) {
    var title by remember { mutableStateOf("") }
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        TextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Note title") },
            placeholder = { Text("For example: Kotlin practice") },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )
    }
}

@Composable
fun RegistrationForm() {
    // 1. Define UI State for inputs
    var email by remember { mutableStateOf("") }

    // 2. Derive validation states dynamically
    val isEmailValid = email.contains("@") && email.contains(".")
    val showError = email.isNotEmpty() && !isEmailValid
    val isButtonEnabled = isEmailValid

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        // 3. Input Field with Inline Validation & Error States
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email Address") },
            isError = showError, // Triggers error border color
            supportingText = {
                if (showError) {
                    Text(
                        text = "Please enter a valid email address.",
                        color = MaterialTheme.colorScheme.error
                    )
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 4. Button Enablement based on validation state
        Button(
            onClick = {
                println(email)
            },
            enabled = isButtonEnabled, // Automatically enables/disables
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Submit")
        }
    }
}

@Composable
fun AddBookForm() {
    var bookTitle by remember { mutableStateOf("") }
    var author by remember { mutableStateOf("") }
    var readingStatus by remember { mutableStateOf<ReadingStatus>(ReadingStatus.WANT_TO_READ) }
    var totalPages by remember { mutableStateOf("") }
    var pagesRead by remember { mutableStateOf("") }
    var rating by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }
    var dateFinished by remember { mutableStateOf("") }

    var expanded by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            text = "Add Book",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onBackground
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = 5.dp,
                    end = 5.dp,
                    top = 20.dp,
                    bottom = 10.dp
                ),
            verticalArrangement = Arrangement.spacedBy(5.dp, Alignment.Top),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = bookTitle,
                onValueChange = { bookTitle = it },
                label = { Text("Book Title") },
                placeholder = { Text("Enter book title. e.g. Paradoxical Sajid") },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            )

            TextField(
                value = author,
                onValueChange = { author = it },
                label = { Text("Book Author") },
                placeholder = { Text("Enter author name. e.g. Arif Azad") },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .background(Color.LightGray, shape)
                    .border(1.dp, Color.DarkGray, shape)
                ,
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Reading Status: "
                )

                Box {
                    OutlinedButton(onClick = { expanded = true }) {
                        Text(readingStatus.name)
                    }

                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        ReadingStatus.values().forEach { status ->
                            DropdownMenuItem(
                                text = { Text(status.name) },
                                onClick = {
                                    readingStatus = status
                                    expanded = false
                                }
                            )
                        }
                    }
                }
            }

            TextField(
                value = totalPages,
                onValueChange = { newPageValue ->
                    if(newPageValue.isEmpty() || newPageValue.all { it.isDigit() }) {
                        totalPages = newPageValue
                    }
                },
                label = { Text("Total Pages") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            )

            OutlinedTextField(
                value = pagesRead,
                onValueChange = { newPageValue ->

                    val filteredPageValue = newPageValue.filter { it.isDigit() }

                    val curReadPages = filteredPageValue.toIntOrNull() ?: 0
                    val enteredTotalPages = totalPages.toIntOrNull() ?: 0

                    if(curReadPages <= enteredTotalPages) {
                        pagesRead = filteredPageValue
                        error = false
                    } else {
                        error = true
                    }
                },
                label = { Text("Pages you Read") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                isError = error,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            )

            TextField(
                value = rating,
                onValueChange = { newPageValue ->
                    if(newPageValue.isEmpty() || newPageValue.all { it.isDigit() }) {
                        rating = newPageValue
                    }
                },
                label = { Text("Book Rating") },
                placeholder = { Text("Enter Book Rating (amoung 1 to 5)") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            )

            TextField(
                value = notes,
                onValueChange = { notes = it },
                label = { Text("Notes") },
                placeholder = { Text("Enter notes for your convenience") },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            )

            Button(onClick = {
                val newBook = Book(
                    id = (books.size+1).toLong(),
                    title = bookTitle,
                    author = author,
                    status = readingStatus,
                    totalPages = totalPages.toIntOrNull() ?: 0,
                    pagesRead = pagesRead.toIntOrNull() ?: 0,
                    rating = if(rating.isNotEmpty()) { rating.toIntOrNull() ?: 0 } else { null },
                    notes = if(notes.isNotEmpty()) { notes } else { null },
                    dateAdded = now,
                    dateFinished = null
                )
                books.add(newBook)
                for(book in books) {
                    println(book)
                }
            }) {
                Text("Submit")
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
fun ScrollableDataList(bookList: List<Book>, modifier: Modifier = Modifier) {
    var query by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }

    val filteredBooks = bookList.filter {
        it.title.contains(query, ignoreCase = true)
    }

    SearchBar(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
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
    book: Book,
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