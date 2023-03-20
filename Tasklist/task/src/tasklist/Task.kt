package tasklist

import kotlinx.datetime.*

enum class Priority {
    C,  //Critical
    H,  //High
    N,  //Normal
    L   //Low
}

enum class Due {
    I,  //In time
    T,  //Today
    O   //Overdue
}

class Task(val lines: MutableList<String>, val priority: Priority, val date: String, val time: String) {

    val due: Due
        get() {
            val taskDate = getDateTime().date
            val currentDate = Clock.System.now().toLocalDateTime(TimeZone.of("UTC+0")).date

            val daysUntil = currentDate.daysUntil(taskDate)

            return if (daysUntil > 0) Due.I
                else if (daysUntil < 0) Due.O
                else Due.T
        }

    fun getLine(lineIndex: Int): String? {
        if (lineIndex !in 0 until lines.size) return null
        var line = ""

        line += lines[lineIndex]
        line += "\n"

        return line
    }

    fun printTask(taskNumber: Int) {

        var output = if (taskNumber in 1 .. 9) "$taskNumber  " else "$taskNumber "
        output += "$date $time ${priority.name} ${due.name}\n"

        for (taskLine in 0 until lines.size) {
            output += "   ${getLine(taskLine)}"
        }
        println(output)
    }

    fun getDateTime(): LocalDateTime {
        val dateSplit = date.split('-')
        val year = dateSplit[0].toInt()
        val month = dateSplit[1].toInt()
        val day = dateSplit[2].toInt()
        val timeSplit = time.split(':')
        val hour = timeSplit[0].toInt()
        val minute = timeSplit[1].toInt()

        return LocalDateTime(year, month, day, hour, minute)
    }

    fun edit(field: String) {

    }

}