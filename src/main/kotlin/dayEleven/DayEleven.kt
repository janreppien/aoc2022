package dayEleven

import AocSolution

class DayEleven : AocSolution(11) {


    override fun solvePartOne(): String {
        val monkeys = createRealMonkeys()
        for (i in 0 until 20) {
            for (monkey in monkeys) {
                monkey.items.map { monkey.operation(it) / 3 }.forEach {
                    monkeys[if (it % monkey.testNumber == 0L) monkey.ifTrue else monkey.ifFalse].items.add(it)
                    monkey.inspectedItem++
                }
                monkey.items.clear()
            }
        }

        return monkeys.sortedByDescending { it.inspectedItem }.toMutableList().subList(0, 2).map { it.inspectedItem }
            .reduce(Long::times).toString()
    }

    override fun solvePartTwo(): String {
        val monkeys = createRealMonkeys()
        val field = monkeys.map { it.testNumber }.reduce { acc, i -> acc * i }
        for (i in 0 until 10000) {
            for (monkey in monkeys) {
                monkey.items.map { monkey.operation(it) % field}.forEach {
                    monkeys[if (it % monkey.testNumber == 0L) monkey.ifTrue else monkey.ifFalse].items.add(it)
                    monkey.inspectedItem++
                }
                monkey.items.clear()
            }
        }
        return monkeys.sortedByDescending { it.inspectedItem }.toMutableList().subList(0, 2).map { it.inspectedItem }
            .reduce(Long::times).toString()
    }

    private fun createTestMonkeys(): MutableList<Monkey> {
        val testMonkeys = mutableListOf<Monkey>()

        testMonkeys.add(Monkey(0, mutableListOf(79, 98), 23, { it * 19 }, 2, 3, 0))
        testMonkeys.add(Monkey(1, mutableListOf(54, 65, 75, 74), 19, { it + 6 }, 2, 0, 0))
        testMonkeys.add(Monkey(2, mutableListOf(79, 60, 97), 13, { it * it }, 1, 3, 0))
        testMonkeys.add(Monkey(3, mutableListOf(74), 17, { it + 3 }, 0, 1, 0))

        return testMonkeys
    }

    private fun createRealMonkeys(): MutableList<Monkey> {
        val monkeys = mutableListOf<Monkey>()

        monkeys.add(Monkey(0, mutableListOf(72, 64, 51, 57, 93, 97, 68), 17, {it * 19}, 4,7,0))
        monkeys.add(Monkey(1, mutableListOf(62), 3, {it * 11}, 3,2,0))
        monkeys.add(Monkey(2, mutableListOf(57, 94, 69, 79, 72), 19, {it + 6}, 0,4,0))
        monkeys.add(Monkey(3, mutableListOf(80, 64, 92, 93, 64, 56), 7, {it + 5}, 2,0,0))
        monkeys.add(Monkey(4, mutableListOf(70, 88, 95, 99, 78, 72, 65, 94), 2, {it + 7}, 7,5,0))
        monkeys.add(Monkey(5, mutableListOf(57, 95, 81, 61), 5, {it * it}, 1,6,0))
        monkeys.add(Monkey(6, mutableListOf(79, 99), 11, {it + 2}, 3,1,0))
        monkeys.add(Monkey(7, mutableListOf(68, 98, 62), 13, {it + 3}, 5,6,0))
        return monkeys
    }

    data class Monkey(
        val id: Int, var items: MutableList<Long>, val testNumber: Int, val operation: (i: Long)
        -> Long, val ifTrue: Int, val ifFalse: Int, var inspectedItem: Long
    )

}