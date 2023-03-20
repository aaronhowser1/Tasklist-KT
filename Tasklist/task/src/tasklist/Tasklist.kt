package tasklist

class Tasklist {
    private val list = mutableListOf<Task>()

    fun add(task: Task) = list.add(task)

    fun remove(taskNumber: Int) {
        if (taskNumber in 1..list.size) list.removeAt(taskNumber-1)
    }

    fun printList() {
        if (list.size == 0) {
            println("No tasks have been input")
            return
        }
        for (taskIndex in 0 until list.size) {
            val task = list[taskIndex]
            task.printTask(taskIndex)
        }
    }
}