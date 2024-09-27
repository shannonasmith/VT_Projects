

/*
    Kotlin problem from last Q&A on 12/7 -> Similar to problem 6 in Scala handout…
 */

fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")

    // "listOf" is a function call, built in to Kotlin and automatically imported
    val kList = listOf("This", "is", "a", "Kotlin", "list") // "kList" for Kotlin list of strings

    println(last(kList)) // give the last element, which is the word "list" above
}

fun <E> last(list: List<E>): E { // looking for last element in a list
    val head = list.first() // gets first element in list
    val tail = list.drop(2) // everything except the first element in the list
    println(tail)
    if (list.isEmpty()) throw NoSuchElementException() // if it makes it here, the list is NOT empty
    return if (tail.isEmpty()) head else last(tail)
}