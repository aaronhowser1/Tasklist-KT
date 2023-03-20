package tasklist

class Tasklist {
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

class Task(vararg lineArgs: String) {
    private val lines = mutableListOf<String>()

    init {
        for (line in lineArgs) lines.add(line)
    }

    override fun toString(): String {
        return lines.joinToString("\n")
    }

}

fun main() {
    showMenu()
}

fun showMenu() {

    val tasklist = Tasklist()

    println("Input an action (add, print, end):")

    while (true) {
        when (readlnOrNull()?.trim()) {
            "add" -> addTask(tasklist)
            "end" -> {
                println("Tasklist ending!")
                break
            }
            "print" -> println(tasklist)
            else -> println("The input action is invalid")
        }
    }

    println(tasklist)
}

fun addTask(tasklist: Tasklist) {

}

fun inputFromPrompt(prompt: String): String {
    println(prompt)
    return readln()
}