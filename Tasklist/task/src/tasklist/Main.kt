package tasklist

import kotlinx.datetime.*

//Automates setting date and time, but not lines nor the menu
const val autorun = true

val tasklist = Tasklist()

fun main() {
    showMenu()
}

fun showMenu() {

    while (true) {
        println("Input an action (add, print, edit, delete, end):")
        when (readlnOrNull()?.trim()) {
            "add" -> addTask(tasklist)
            "end" -> {
                println("Tasklist exiting!")
                break
            }
            "print" -> printFancy()
            "simple" -> print()
            "edit" -> {
                if (tasklist.size() != 0) print()
                edit()
            }
            "delete" -> {
                if (tasklist.size() == 0) {
                    println("No tasks have been input")
                } else {
                    print()
                    deleteTask()
                }
            }
            else -> println("The input action is invalid")
        }
    }
}

fun printFancy() = tasklist.printListFancy()

fun print() = tasklist.printList()

fun edit() {
    if (tasklist.size() == 0) {
        println("No tasks have been input")
        return
    }

    val input = inputFromPrompt("Input the task number (1-${tasklist.size()}):")
    if (input.lowercase() == "exit") return

    try {
        val taskNumber = input.toInt()
        if (taskNumber !in 1..tasklist.size()) throw IllegalArgumentException()
    } catch (e: Exception) {
        println("Invalid task number")
        edit()
        return
    }

    val task = tasklist.getTask(input.toInt())

    while (true) {
        when (inputFromPrompt("Input a field to edit (priority, date, time, task):")) {
            "priority" -> {
                task.priority = inputPriority()
                break
            }
            "date" -> {
                task.date = inputDate()
                break
            }
            "time" -> {
                task.time = inputTime()
                break
            }
            "task" -> {
                task.lines = inputTaskLines()
                break
            }
            else -> println("Invalid field")
        }
    }

    println("The task is changed")

}

fun deleteTask() {
    val input = inputFromPrompt("Input the task number (1-${tasklist.size()}):")
    if (input.lowercase() == "exit") return
    if (!tasklist.remove(input)) deleteTask()
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
    val input = if (autorun) {
        println("Input the task priority (C, H, N, L):\n> C")
        "C"
    } else {
        inputFromPrompt("Input the task priority (C, H, N, L):")
    }
    return when (input.lowercase()) {
        "c" -> Priority.C
        "h" -> Priority.H
        "n" -> Priority.N
        "l" -> Priority.L
        else -> inputPriority()
    }
}

fun inputDate(): String {
    val input = if (autorun) {
        println("Input the date (yyyy-mm-dd):\n> 2023-03-20")
         "2023-03-20"
    } else {
        inputFromPrompt("Input the date (yyyy-mm-dd):")
    }
    try {
        val inputSplit = input.split('-')

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
    val input = if (autorun) {
        println("Input the time (hh:mm):\n> 12:00")
        "12:00"
    } else {
        inputFromPrompt("Input the time (hh:mm):")
    }
    try {
        if (input.split(':').size != 2) throw RuntimeException("Time is not in hh:mm")

        var hour = input.split(':').first()
        var minute = input.split(':').last()

        if (hour.toInt() !in 0..23) throw RuntimeException("Hour $hour is not in 0-23")
        if (minute.toInt() !in 0..59) throw RuntimeException("Minute $minute is not in 0-59")

        // Unused, but will throw RuntimeException if invalid
        val testLocalDateTime = LocalDateTime(1, 1, 1, hour.toInt(), minute.toInt())

        if (hour.length == 1) hour = "0$hour"
        if (minute.length == 1) minute = "0$minute"

        return "$hour:$minute"

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

fun inputFromPrompt(prompt: String): String {
    println(prompt)
    return readln()
}
