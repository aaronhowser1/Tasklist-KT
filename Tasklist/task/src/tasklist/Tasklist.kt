package tasklist

import kotlinx.datetime.*

class Tasklist {
    private val list = mutableListOf<Task>()

    fun add(task: Task) = list.add(task)

    fun remove(taskNumber: Int) {
        if (taskNumber in 1..list.size) list.removeAt(taskNumber-1)
    }

    override fun toString(): String {
        if (list.size == 0) return "No tasks have been input"
        var output = ""
        for (taskIndex in 0 until list.size) {
            val task = list[taskIndex]
            for (taskLine in 0 until task.lines.size) {
                output += if (taskLine == 0) taskIndex+1 else " "
                output += if (taskIndex in 0..8) "  " else " "
                output += task.lines[taskLine]
                output += "\n"
                // Example:
                // 1  Supermarket (taskLine[0])
                //   -----------  (taskLine[1])
                //   butter       (taskLine[2])
                //   milk         (taskLine[3])
                //   meat         (taskLine[4])
            }
            output += "\n"
        }
        return output
    }
}