fun <T> getStringsOnly(list: List<T>): List<String> {
    val result = mutableListOf<String>()
    // make your code here
    for (item in list) if (item is String) result.add(item)

    return result
}