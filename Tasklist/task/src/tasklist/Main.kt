package tasklist

class Tasklist {
    private val list = mutableListOf<Task>()

    fun add(task: Task) = list.add(task)

    fun remove(taskNumber: Int) {
        if (taskNumber in 1..list.size) list.removeAt(taskNumber-1)
    }

    override fun toString(): String {
        if (list.size == 0) return "No tasks have been input."
        var output = ""
        for (taskIndex in 0 until list.size) {
            val task = list[taskIndex]
            for (taskLine in 0 until task.lines.size) {
                output += if (taskIndex == 0) taskLine+1 else " "
                output += "  "
                output += task.lines[taskLine]
                output += "\n"
                // Example:
                // 1  Supermarket (taskLine[0])
                //   -----------  (taskLine[1])
                //   butter       (taskLine[2])
                //   milk         (taskLine[3])
                //   meat         (taskLine[4])
            }
        }
        return output
    }
}

class Task(vararg lineArgs: String) {
    val lines = mutableListOf<String>()

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