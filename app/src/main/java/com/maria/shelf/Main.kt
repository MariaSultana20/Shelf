package com.maria.shelf

import android.R
import org.jetbrains.annotations.NotNull

data class Book(val title: String, val totalPages: Int)

fun main() {
    ///Printing
    print("Hello, World!!\n")

    //value assignment -> "val" is constant
    val x = 10
    println(x)
    //x = 100 -> gets error

    //explicit val type
    val y: Int = 20
    println(y)

    ///Float and Double
    val pi: Float = 3.1416f
    println(pi)

    val pi_in_double = 3.1416f
    println(pi_in_double)

    //Boolean
    val isOk: Boolean = true
    println(isOk)

    // variables
    var greetings = "Hello World!!"
    greetings = "Hello, Maria's Fan!!"
    println(greetings)

    // string interpolation
    val curNumber = 10
    println("Is curNumber an even number? ${curNumber % 2 == 0}")

    // Console input
    println("Enter a number and find if it's even: ")
    val input = readln()
//    val inputAsInteger = input.toInt()
//    println("Your entered number is even: ${inputAsInteger % 2 == 0}")

//    val inputAsInteger = input.toIntOrNull() ?: 0
//    println("Your entered number is even: ${inputAsInteger % 2 == 0}")

//    val inputAsInteger = input.toIntOrNull()?.rem(2)?.equals(0)
//    println("Your entered number is even: ${inputAsInteger}")

    // try...catch
//    val inputAsInteger = try {
//        input.toInt()
//    } catch (e: NumberFormatException) {
//        0
//    }

    val inputAsInteger = input.toIntOrNull()

    if(inputAsInteger != null) {
        val isEven = inputAsInteger % 2 == 0
        if(isEven) {
            println("The number is even!")
        } else {
            println("The number is odd!")
        }
    } else {
        println("Enter a valid number")
    }

    // when expression
    val output = when(inputAsInteger) {
        null -> "Dude, enter a valid number!"
        3 -> "The number is three."
        5 -> "The number is five."
        in 10..20 -> "The number is between 10 and 20."
        else -> "Don't know what type of number it is."
    }
    println(output)

    // Arrays
    val favouriteNumber = intArrayOf(1, 2, 5, 20) + 10
    println("The array is: ${favouriteNumber.contentToString()}")
    println("Second number of the array is: ${favouriteNumber.getOrNull(1)}")

    // Loop
//    var i = 0
//    var sum = 0
//    while(i < favouriteNumber.size) {
//        println("Number at the position ${i+1} is: ${favouriteNumber[i]}")
//        sum += favouriteNumber[i]
//        i++
//    }
//    println("Total sum of the array is ${sum}")


    // Functions
    var sum = 0

//    for (i in 0..favouriteNumber.lastIndex)

//    for(number in favouriteNumber) {
//        sum += number
//    }

    for (i in 0 until favouriteNumber.size) {
        println("Number at the position ${i+1} is: ${favouriteNumber[i]}")
        sum += favouriteNumber[i]
    }
    println("Total sum of the array is: ${sum}")

    val reversedString = greetings.reversed()
    println("Main string: ${greetings}\nReversed string: ${reversedString}")

    /// Lambda Function
    val str: String = "He2l1l43o"

//    val lambda: (Char) -> Boolean = { it.isLetter() }
//    val lettersOnly: String = str.filter(lambda)

//    val lettersOnly: String = str.filter {
//        it.isLetter()
//    }

//    val lettersOnly: String = str.filter { currentCharacter ->
//        currentCharacter.isLetter()
//    }

    val lettersOnly: String = str.myFilter { it.isLetter() }

    println("The letter only text is: ${lettersOnly}")

    // Week-1: Sun: Function
    println(formatDuration(minutes = 100))
    println(formatDuration(minutes = 24))
    println(formatDuration(minutes = 120))
    println(formatDuration(minutes = 145))
    println(formatDuration(minutes = 119))

    // Week-1: Mon: Nullability Check
    println(getUserName(userName = "Maria"))
    println(getUserName(userName = null))

    println("Is even: ${isEven(numberAsString = "10")}")
    println("Is even: ${isEven(numberAsString = "5")}")
    println("Is even: ${isEven(numberAsString = "Hii")}")

    // Week-1: Tue: Collections and lambdas
    val books = listOf(
            Book("Power of Your Subconscious Mind", totalPages = 215),
            Book("Time Management", totalPages = 128),
            Book("Never Stop Learning", totalPages = 140),
            Book("The Almanack of Naval Ravikant", totalPages = 239),
            Book("Steal Like an Artist", totalPages = 140),
            Book("Prottaborton", totalPages = 221),
            Book("The Jungle Book", totalPages = 216),
            Book("The Kite Runner", totalPages = 324),
            Book("A Thousand Splendid Suns", totalPages = 372),
            Book("Competitive Programmer's Handbook", totalPages = 285),
            Book("Paradoxical Sajid", totalPages = 160),
            Book("Paradoxical Sajid 2", totalPages = 225),
            Book("Revive Your Heart", totalPages = 160),
            Book("Satkahon", totalPages = 728),
            Book("The Miracle Morning", totalPages = 304)
        )
    val topThreeBooks = books
        .sortedByDescending { it.totalPages }
        .take(3)
    println("Top three books having most number of pages:\n ${topThreeBooks}")

    val groupByFirstLetter: Map<Char, List<Book>> = books.groupBy { it.title.first() }
    println(groupByFirstLetter)

    val topBooksInEachGroup = groupByFirstLetter.map {
        it.value
            .sortedByDescending { it.totalPages }
            .take(3)
    }
    println(topBooksInEachGroup)

    // Week - 1: Wed: Control flow
    val pageProgressPercentage = 42.3

    val status: String = when(pageProgressPercentage) {
        0.0 -> "Not Started"
        in 0.1 .. 99.9 -> "Reading"
        else -> "Finished"
    }
    println("Reading status is: ${status}")
}

/// Week - 1: Sunday
fun formatDuration(minutes: Int): String {
    val hours = minutes/60
    val output: String = "${hours}h ${minutes%60}m"
    return output
}

fun getUserName(userName: String?): String {
    if (userName == null) return "Guest"
    return userName
}

fun isEven(numberAsString: String): Boolean {
    val isEven = numberAsString.toIntOrNull()?.rem(2)?.equals(0) ?: false
    return isEven
}

// Normal Functions
//fun reversed(stringToReverse: String): String {
//    return buildString {
//        for(i in stringToReverse.lastIndex downTo 0) {
//            append(stringToReverse[i])
//        }
//    }
//}
fun String.reversed(): String {
    return buildString {
        for(i in this@reversed.lastIndex downTo 0) {
            append(this@reversed[i])
        }
    }
}
fun String.myFilter(predicate: (Char) -> Boolean): String {
    return buildString{
        for(char in this@myFilter) {
            if(predicate(char)) {
                append(char)
            }
        }
    }
}