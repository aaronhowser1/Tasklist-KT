package tasklist

class TaskList {
    private val list = mutableListOf<String>()

    fun add(task: String) = list.add(task)

    fun remove(taskNumber: Int) {
        if (taskNumber in 1..list.size) list.removeAt(taskNumber-1)
    }

    override fun toString(): String {
        if (list.size == 0) return "No tasks have been input."
        var output = ""
        for (i in 0 until list.size) {
            output += "${i+1} "
            if (i in 0..8) output+= " "
            output += "${list[i]}\n"
        }
        return output
    }
}

fun main() {
    showMenu()
}

fun showMenu() {

    val taskList = TaskList()

    println("Input the tasks (enter a blank line to end):")

    while (true) {
        val input = readlnOrNull()
        if (input.isNullOrBlank()) break else taskList.add(input)
    }

    println(taskList)
}

fun inputFromPrompt(prompt: String): String {
    println(prompt)
    return readln()
}