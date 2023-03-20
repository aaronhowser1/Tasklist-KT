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

}