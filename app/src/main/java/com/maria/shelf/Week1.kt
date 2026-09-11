package com.maria.shelf
data class Book(val title: String, val totalPages: Int)

fun main() {
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