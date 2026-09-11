package com.maria.shelf

import androidx.compose.ui.Modifier
import kotlin.math.sqrt

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

    // Data and Classes
    val rect1 = Rectangle(width = 10f, height = 10f)
    println("Width of rect1 is: ${rect1.width}")
    println("Height of rect1 is: ${rect1.height}")
    println("Diagonal of rect1 is: ${rect1.diagonal}")

//    val rect2 = Rectangle(width = 21f, height = 100f)
//    println(rect2.area)

    val rect2 = rect1.copy(height = 20.0F)
    println(rect2.width)
    println(rect2.diagonal)

    println("Max area between two rectangles is: ${maxArea(rect1, rect2)}")
}
data class Rectangle(val width: Float, val height: Float) {
    val area = width * height
    val diagonal = sqrt(width * width + height * height)
}

fun maxArea(rect1: Rectangle, rect2: Rectangle): Float {
    val area1 = rect1.area
    val area2 = rect2.area
    return maxOf(area1, area2)
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