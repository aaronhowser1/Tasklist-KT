package tasklist

import kotlinx.datetime.*
import java.lang.RuntimeException

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

    val priority = inputPriority()

    val date = inputDate()
    val time = inputTime()
    val dateTime = createDateTime(date, time)

    val taskLines = inputTaskLines()

    tasklist.add(Task(taskLines, priority, dateTime))
}

fun inputPriority(): Priority {
    val input = inputFromPrompt("Input the task priority (C, H, N, L):")
    return when (input.lowercase()) {
        "c" -> Priority.C
        "h" -> Priority.H
        "n" -> Priority.N
        "l" -> Priority.L
        else -> inputPriority()
    }
}

fun inputDate(): LocalDate {
    val input = inputFromPrompt("Input the date (yyyy-mm-dd):")
    try {
        val inputSplit = input.split('-')
        val date = LocalDate(inputSplit[0].toInt(),inputSplit[1].toInt(),inputSplit[2].toInt())
        return date
    } catch (e: RuntimeException) {
        println("The input date is invalid")
        return inputDate()
    }
}

fun inputTime(): String {
    val input = inputFromPrompt("Input the time (hh:mm):")
    try {
        if (input.split(':').size != 2) throw RuntimeException("Time is not in hh:mm")

        val hour = input.split(':').first().toInt()
        val minute = input.split(':').last().toInt()

        if (hour !in 0..23) throw RuntimeException("Hour $hour is not in 0-23")
        if (minute !in 0..59) throw RuntimeException("Minute $minute is not in 0-59")

        //Unused, but will throw RuntimeException if invalid
        val testLocalDateTime = LocalDateTime(1, 1, 1, hour, minute)

        return input

    } catch (e: RuntimeException) {
        println("The input time is invalid")
        return inputTime()
    }
}

fun createDateTime(localDate: LocalDate, time: String): LocalDateTime {

    val year = localDate.year
    val month = localDate.monthNumber
    val day = localDate.dayOfMonth
    val hour = time.split(':').first().toInt()
    val minute = time.split(':').last().toInt()

    return LocalDateTime(year, month, day, hour, minute)
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
        return inputTaskLines()
    }
    return inputLines
}

fun inputFromPrompt(prompt: String): String {
    println(prompt)
    var input = readlnOrNull()
    if (input.isNullOrBlank()) input = inputFromPrompt(prompt)
    return input
}