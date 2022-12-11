package dayEleven

import AocSolution

class DayEleven : AocSolution(11) {

    val monkeys = createTestMonkeys()

    override fun solvePartOne(): String {
        for(i in 0 until 20) {
            for(monkey in monkeys) {
                monkey.items = monkey.items.map { monkey.operation(it) }.toMutableList()
                monkey.items = monkey.items.map { it / 3 }.toMutableList()
                monkey.items.forEach {
                    if(monkey.test(it)) {
                        monkeys[monkey.ifTrue].items.add(it)
                    } else {
                        monkeys[monkey.ifFalse].items.add(it)
                    }
                    monkey.inspectedItem++
                }
                monkey.items = mutableListOf()
            }
            //println("Round $i")
            //for (monkey in monkeys) {
            //    println("Monkey ${monkey.id}: ${monkey.items}")
            //}
        }
        val sortedMonkeys = monkeys.sortedByDescending { it.inspectedItem }

         return monkeys.sortedByDescending { it.inspectedItem }.toMutableList().subList(0,2).map { it.inspectedItem }
             .reduce(Long::times).toString()

        /*monkeys.forEach {
            println("Monkey ${it.id}: ${it.inspectedItem}")
        }
        return (monkeys[0].inspectedItem * monkeys[1].inspectedItem).toString()
         */
    }

    override fun solvePartTwo(): String {
        for(i in 0 until 10000) {
            for(monkey in monkeys) {
                monkey.items = monkey.items.map { monkey.operation(it)}.toMutableList()
                monkey.items.forEach {
                    if(monkey.test(it)) {
                        monkeys[monkey.ifTrue].items.add(it)
                    } else {
                        monkeys[monkey.ifFalse].items.add(it)
                    }
                    monkey.inspectedItem++
                }
                monkey.items = mutableListOf()
            }
        }
        val sortedMonkeys = monkeys.sortedByDescending { it.inspectedItem }

        return monkeys.sortedByDescending { it.inspectedItem }.toMutableList().subList(0,2).map { it.inspectedItem }
            .reduce(Long::times).toString()
    }

    private fun createTestMonkeys(): MutableList<Monkey> {
        val testMonkeys = mutableListOf<Monkey>()

        testMonkeys.add(Monkey(0, mutableListOf(79,98), {it % 23 == 0L}, {it * 19}, 2,3,0))
        testMonkeys.add(Monkey(1, mutableListOf(54,65,75,74), {it % 19 == 0L}, {it + 6}, 2,0,0))
        testMonkeys.add(Monkey(2, mutableListOf(79,60,97), {it % 13 == 0L}, {it * it}, 1,3,0))
        testMonkeys.add(Monkey(3, mutableListOf(74), {it % 17 == 0L}, {it +3}, 0,1,0))

        return testMonkeys
    }

    private fun createRealMonkeys(): MutableList<Monkey> {
        val monkeys = mutableListOf<Monkey>()

        monkeys.add(Monkey(0, mutableListOf(72, 64, 51, 57, 93, 97, 68), {it % 17 == 0L}, {it * 19}, 4,7,0))
        monkeys.add(Monkey(1, mutableListOf(62), {it % 3 == 0L}, {it * 11}, 3,2,0))
        monkeys.add(Monkey(2, mutableListOf(57, 94, 69, 79, 72), {it % 19 == 0L}, {it + 6}, 0,4,0))
        monkeys.add(Monkey(3, mutableListOf(80, 64, 92, 93, 64, 56), {it % 7 == 0L}, {it + 5}, 2,0,0))
        monkeys.add(Monkey(4, mutableListOf(70, 88, 95, 99, 78, 72, 65, 94), {it % 2 == 0L}, {it + 7}, 7,5,0))
        monkeys.add(Monkey(5, mutableListOf(57, 95, 81, 61), {it % 5 == 0L}, {it * it}, 1,6,0))
        monkeys.add(Monkey(6, mutableListOf(79, 99), {it % 11 == 0L}, {it + 2}, 3,1,0))
        monkeys.add(Monkey(7, mutableListOf(68, 98, 62), {it % 13 == 0L}, {it + 3}, 5,6,0))

        return monkeys
    }

}