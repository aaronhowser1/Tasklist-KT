package tasklist

class Tasklist {
    private val list = mutableListOf<Task>()

    fun add(task: Task) = list.add(task)

    // Returns true if it worked
    fun remove(input: String): Boolean {
        try {
            val taskNumber = input.toInt()
            if (taskNumber in 1..list.size) {
                list.removeAt(taskNumber - 1)
                println("The task is deleted")
                return true
            } else throw IllegalArgumentException()
        } catch (e: Exception) {
            println("Invalid task number")
            return false
        }
    }

    fun printList() {
        if (list.size == 0) {
            println("No tasks have been input")
            return
        }
        for (taskIndex in 0 until list.size) {
            val task = list[taskIndex]
            task.printTask(taskIndex+1)
        }
    }

    fun printListFancy() {

        if (list.size == 0) {
            println("No tasks have been input")
            return
        }

        var output = """
            +----+------------+-------+---+---+--------------------------------------------+
            | N  |    Date    | Time  | P | D |                   Task                     |
            +----+------------+-------+---+---+--------------------------------------------+
        """.trimIndent()



    }

    fun getTask(taskNumber: Int) = list[taskNumber-1]

    fun size() = list.size
}