package tasklist

import kotlinx.datetime.*

const val MAX_LINE_WIDTH = 44

enum class Priority(val color: String) {
    C("\u001B[101m \u001B[0m"),  //Critical
    H("\u001B[103m \u001B[0m"),  //High
    N("\u001B[102m \u001B[0m"),  //Normal
    L("\u001B[104m \u001B[0m")   //Low
}

enum class Due(val color: String) {
    I("\u001B[102m \u001B[0m"),  //In time
    T("\u001B[103m \u001B[0m"),  //Today
    O("\u001B[101m \u001B[0m")   //Overdue
}

class Task(var lines: MutableList<String>, var priority: Priority, var date: String, var time: String) {

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

    fun splitLine(lineIndex: Int): MutableList<String> {
        var line = lines[lineIndex]
        val outputLines = mutableListOf<String>()
        while (true) {
            if (line.length <= MAX_LINE_WIDTH) {
                outputLines.add(line)
                break
            }
            outputLines.add(line.substring(0, MAX_LINE_WIDTH))
            line = line.substring(MAX_LINE_WIDTH)
        }
//        println("The line $line has been split into the list $outputLines")
        return outputLines
    }

    fun getFancy(taskNumber: Int): String {

        val allSplitLines = mutableListOf<String>()
        for (lineIndex in 0 until lines.size) {
            val splitLines = splitLine(lineIndex)
            for (splitLine in splitLines) allSplitLines.add(splitLine)
        }

        val taskHeight = allSplitLines.size

        var output = ""

        for (i in 0  until  taskHeight) {
            output += "\n"
            if (i == 0) {
                //First line of task
                if (taskNumber in 1..9) {
                    //Two spaces for 1-9
                    output += "| $taskNumber  "
                } else {
                    //One space for 10+
                    output += "| $taskNumber "
                }
                output += "| $date | $time | ${priority.color} | ${due.color} |"
            } else {
                //N-D
                output += "|    |            |       |   |   |"
            }

            val currentLineSplice = allSplitLines[i]
            output += currentLineSplice

            val spacesNeeded = MAX_LINE_WIDTH - currentLineSplice.length
            if (spacesNeeded > 0) output += " ".repeat(spacesNeeded)
            output += "|"
        }
        return output
    }

}