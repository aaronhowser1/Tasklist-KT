package tasklist

import kotlinx.datetime.*

enum class Priority {
    C,  //Critical
    H,  //High
    N,  //Normal
    L   //Low
}

class Task(val lines: MutableList<String>, val priority: Priority, val localDateTime: LocalDateTime) {

//    override fun toString(): String {
//        return lines.joinToString("\n")
//    }

    fun getLine(lineIndex: Int): String? {
        if (lineIndex !in 0 until lines.size) return null
        var line = ""

        line += lines[lineIndex]
        line += "\n"

        return line

    }

    fun getDateTime(): String {
        val year = localDateTime.year
        val month = localDateTime.monthNumber
        val day = localDateTime.dayOfMonth

        var hour = localDateTime.hour.toString()
        if (hour.length == 1) hour = "0$hour"

        var minute = localDateTime.minute.toString()
        if (minute.length == 1) minute = "0$minute"

        return "$year-$month-$day $hour:$minute"

    }

    fun printTask(taskNumber: Int) {

        var output = if (taskNumber in 1 .. 9) "$taskNumber  " else "   "
        output += "${getDateTime()} ${priority.name}\n"

        for (taskLine in 0 until lines.size) {
            output += "   ${getLine(taskLine)}"
        }
        println(output)
    }

}