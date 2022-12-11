package dayEleven

data class Monkey(
    val id: Int, var items: MutableList<Long>, val test: (i: Long) -> Boolean, val operation: (i: Long)
    -> Long, val ifTrue: Int, val ifFalse: Int, var inspectedItem: Long
) {
}