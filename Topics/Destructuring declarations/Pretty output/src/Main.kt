data class Comment(val id: Int, val body: String, val author: String)

fun printComments(commentsData: MutableList<Comment>){
    for ((_,text,author) in commentsData) {
        println("Author: $author; Text: $text")
    }
}