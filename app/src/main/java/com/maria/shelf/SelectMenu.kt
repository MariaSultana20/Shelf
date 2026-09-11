package com.maria.shelf

data class Book(val title: String)
fun main() {
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
                        println("Book at index: ${i}, has title: ${books[i].title}")
                    }
                }
            } else if(selectedMenu == 3) {
                print("Enter the book index you want to update: ")
                val bookIndexToUpdate = readln().toInt()
                if(bookIndexToUpdate >= books.size) {
                    println("Index out of bound. Please, enter a valid index")
                } else {
                    print("Enter updated book title: ")
                    val bookTitle = readln()
                    for (i in 0 until books.size) {
                        if(i == bookIndexToUpdate) {
                            val newCurBook = books[i].copy(bookTitle)
                            books[i] = newCurBook
                        }
                    }
                    println("Updated a book of title: ${bookTitle} at index: ${bookIndexToUpdate}")
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