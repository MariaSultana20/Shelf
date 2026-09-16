package com.maria.shelf

import android.os.Message
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

data class Book(
    var title: String,
    val readingStatus: ReadingStatus = ReadingStatus.WANT_TO_READ
)
enum class ReadingStatus {
    WANT_TO_READ, READING, FINISHED
}

sealed class Result {
    object success: Result()
    data class error(val errorMessage: String): Result()
}
fun handleSaveResults(result: Result) {
    when(result) {
        is Result.success -> {
            println("Successful result")
        }
        is Result.error -> {
            println("Error result")
        }
    }
}
suspend fun main() {
    // Week-2: Monday: Enums, sealed classes, interfaces
    val res = Result.success
    handleSaveResults(res)

    // Week-2: Tue: Sep15: Extensions and scope functions
    val textBook = Book("Learn Kotlin with Fun", readingStatus = ReadingStatus.READING)
    println("Reading process: ${textBook.progressPercent()}")

    // Week-2: Wed: Sep16: Coroutines, lightly
    withContext(Dispatchers.Default) {
        this.launch {
            loadBooks()
        }
        this.launch {
            println("The 'CoroutineScope.launch()' running on ${Thread.currentThread().name}")
            delay(1000)
        }
        println("The 'withContext()' running on ${Thread.currentThread().name}")
    }

    //Week-1: Thurs: Shelf, as a console program
    var books = mutableListOf<Book>()
    while(true) {
        println("If you want to 'Add a Book' then enter '1'")
        println("If you want to 'List of All Books' then enter '2'")
        println("If you want to 'Update a Book' then enter '3'")
        println("If you want to 'Delete a Book' then enter '4'")
        val input = readln()
        val selectedMenu = input.toIntOrNull()
        if(selectedMenu != null) {
            if(selectedMenu == 1) {
                print("Enter a book title: ")
                val bookTitle = readln()
                val curBook = Book(bookTitle)
                books.add(curBook)
                println("Added a book of title: ${bookTitle} at index: ${books.size - 1}")
            } else if(selectedMenu == 2) {
                if(books.size == 0) {
                    println("There's no book in the book list.")
                } else {
                    println("Here's the list of all books:")
                    for (i in 0 until books.size) {
                        println("Book at index-${i} is: ${books[i]}")
                    }
                }
            } else if(selectedMenu == 3) {
                print("Enter the book index you want to update: ")
                val bookIndexToUpdate = readln().toIntOrNull()
                if(bookIndexToUpdate == null) {
                    println("Please, enter a valid index")
                } else {
                    if(bookIndexToUpdate >= books.size) {
                        println("Index out of bound. Please, enter a valid index")
                    } else {
                        print("Enter updated book title: ")
                        val prevBookTitle = books[bookIndexToUpdate].title
                        val bookTitle = readln()
//                    for (i in 0 until books.size) {
//                        if(i == bookIndexToUpdate) {
//                            val newCurBook = books[i].copy(bookTitle)
//                            books[i] = newCurBook
//                        }
//                    }
                        books.map { book ->
                            book.apply {
                                if(this.title == prevBookTitle)
                                    title = bookTitle
                            }
                        }
                        println("Updated a book of title: ${bookTitle} at index: ${bookIndexToUpdate}")
                    }
                }
            } else if(selectedMenu == 4) {
                print("Enter the book title you want to delete: ")
                val bookTitle = readln()
                val curBook = Book(bookTitle)
                val numberOfBooks = books.size
                books.remove(curBook)
                if(numberOfBooks == books.size) {
                    println("Could not find this book. Please, try again!!")
                } else {
                    println("Removed a book of title: ${bookTitle} from the book list")
                }
            } else {
                println("Please, enter a valid option")
            }
        } else {
            println("Please, enter a valid option")
        }
    }
}

// Week-2: Tue: Sep15: Extensions and scope functions
fun Book.progressPercent(): Int {
    val progress = when(this.readingStatus) {
        ReadingStatus.WANT_TO_READ -> 0
        ReadingStatus.READING -> 45
        ReadingStatus.FINISHED -> 100
    }
    return progress
}

// Week-2: Wed: Sep16: Coroutines, lightly
suspend fun loadBooks() {
    println("The 'loadBooks()' function running on ${Thread.currentThread().name}")
    delay(2000)
    //TODO: call api for loading books from database
}