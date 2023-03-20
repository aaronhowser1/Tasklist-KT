package tasklist

import kotlinx.datetime.*

enum class Priority {
    C,  //Critical
    H,  //High
    N,  //Normal
    L   //Low
}

class Task(val lines: MutableList<String>, val priority: Priority, val localDateTime: LocalDateTime) {

    override fun toString(): String {
        return lines.joinToString("\n")
    }

    fun getLine(lineIndex: Int): String? {
        if (lineIndex !in 0 until lines.size) return null
        var line = ""

        line += lines[lineIndex]
        if (lineIndex == 0) line += " ${priority.name}"
        line += "\n"

        return line

    }

}