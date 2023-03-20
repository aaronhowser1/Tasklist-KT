package tasklist

import kotlinx.datetime.*

fun main() {
    showMenu()
}

fun showMenu() {

    val tasklist = Tasklist()

    while (true) {
        println("Input an action (add, print, end):")
        when (readlnOrNull()?.trim()) {
            "add" -> addTask(tasklist)
            "end" -> {
                println("Tasklist exiting!")
                break
            }
            "print" -> println(tasklist)
            else -> println("The input action is invalid")
        }
    }
}

fun addTask(tasklist: Tasklist) {

    println("Input a new task (enter a blank line to end):")
    val inputLines = mutableListOf<String>()
    while (true) {
        val input = readlnOrNull()?.trim()
        if (input.isNullOrBlank()) break
        inputLines.add(input)
    }

    if (inputLines.isEmpty()) {
        println("The task is blank")
        return
    }

    tasklist.add(Task(inputLines))
}

fun setPriority(): Priority {
    val input = inputFromPrompt("Input the task priority (C, H, N, L):")
    return when (input.lowercase()) {
        "c" -> Priority.C
        "h" -> Priority.H
        "n" -> Priority.N
        "l" -> Priority.L
        else -> setPriority()
    }
}

fun inputFromPrompt(prompt: String): String {
    println(prompt)
    var input = readlnOrNull()
    if (input.isNullOrBlank()) input = inputFromPrompt(prompt)
    return input
}