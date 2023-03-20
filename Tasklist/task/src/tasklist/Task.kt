package tasklist


enum class Priority {
    C,  //Critical
    H,  //High
    N,  //Normal
    L   //Low
}

class Task(val lines: MutableList<String>) {

    override fun toString(): String {
        return lines.joinToString("\n")
    }

}