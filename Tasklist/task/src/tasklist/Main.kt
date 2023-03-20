package tasklist

import kotlinx.datetime.*
import java.lang.RuntimeException

const val debug = false

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
            "print" -> tasklist.printList()
            else -> println("The input action is invalid")
        }
    }
}

fun addTask(tasklist: Tasklist) {

    val priority = inputPriority()

    val date = inputDate()
    val time = inputTime()

    val taskLines = inputTaskLines()
    if (taskLines.isEmpty()) return

    tasklist.add(Task(taskLines, priority, date, time))
}

fun inputPriority(): Priority {
    val input = if (debug) {
        println("Input the task priority (C, H, N, L):\n> C")
        "C"
    } else {
        inputFromPrompt("Input the task priority (C, H, N, L):")
    }
    return when (input?.lowercase()) {
        "c" -> Priority.C
        "h" -> Priority.H
        "n" -> Priority.N
        "l" -> Priority.L
        else -> inputPriority()
    }
}

fun inputDate(): String {
    val input = if (debug) {
        println("Input the date (yyyy-mm-dd):\n> 2023-03-20")
         "2023-03-20"
    } else {
        inputFromPrompt("Input the date (yyyy-mm-dd):")
    }
    try {
        val inputSplit = input!!.split('-')

        // Will throw RuntimeException if invalid
        val date = LocalDate(inputSplit[0].toInt(),inputSplit[1].toInt(),inputSplit[2].toInt())

        val year = date.year.toString()
        var month = date.monthNumber.toString()
        var day = date.dayOfMonth.toString()

        if (month.length == 1) month = "0$month"
        if (day.length == 1) day = "0$day"

        return "$year-$month-$day"
    } catch (e: RuntimeException) {
        println("The input date is invalid")
        return inputDate()
    }
}

fun inputTime(): String {
    val input = if (debug) {
        println("Input the time (hh:mm):\n> 12:00")
        "12:00"
    } else {
        inputFromPrompt("Input the time (hh:mm):")
    }
    try {
        if (input?.split(':')?.size != 2) throw RuntimeException("Time is not in hh:mm")

        val hour = input.split(':').first().toInt()
        val minute = input.split(':').last().toInt()

        if (hour !in 0..23) throw RuntimeException("Hour $hour is not in 0-23")
        if (minute !in 0..59) throw RuntimeException("Minute $minute is not in 0-59")

        // Unused, but will throw RuntimeException if invalid
        val testLocalDateTime = LocalDateTime(1, 1, 1, hour, minute)

        return input

    } catch (e: RuntimeException) {
        println("The input time is invalid")
        return inputTime()
    }
}

fun inputTaskLines(): MutableList<String> {
    println("Input a new task (enter a blank line to end):")
    val inputLines = mutableListOf<String>()
    while (true) {
        val input = readlnOrNull()?.trim()
        if (input.isNullOrBlank()) break
        inputLines.add(input)
    }

    if (inputLines.isEmpty()) {
        println("The task is blank")
        return mutableListOf()
    }
    return inputLines
}

fun inputFromPrompt(prompt: String): String? {
    println(prompt)
    return readln()
}