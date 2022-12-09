package dayNine

import AocSolution
import java.io.File


class DayNine : AocSolution(9) {

    data class Instruction(val direction: Char, val amount: Int)

    private val input = readInput()

    private fun readInput(): MutableList<Instruction> {
        val input = mutableListOf<Instruction>()
        val file = File("src/main/resources/inputs/dayNine/input.txt")

        file.forEachLine {
            input.add(Instruction(it.split(" ")[0][0], it.split(" ")[1].toInt()))
        }
        return input
    }

    override fun solvePartOne(): String {
        var head = Pair(0, 0)

        var tail = Pair(0, 0)
        val visitedPositions = mutableListOf(tail)

        input.forEach {
            for (i in 0 until it.amount) {
                when (it.direction) {
                    'L' -> head = Pair(head.first, head.second - 1)
                    'R' -> head = Pair(head.first, head.second + 1)
                    'U' -> head = Pair(head.first + 1, head.second)
                    'D' -> head = Pair(head.first - 1, head.second)
                }
                if (!directNeighbours(head, tail)) {
                    if (head.first == tail.first || head.second == tail.second) {
                        when (it.direction) {
                            'L' -> tail = Pair(tail.first, tail.second - 1)
                            'R' -> tail = Pair(tail.first, tail.second + 1)
                            'U' -> tail = Pair(tail.first + 1, tail.second)
                            'D' -> tail = Pair(tail.first - 1, tail.second)
                        }
                    } else {
                        when (it.direction) {
                            'L' -> tail = Pair(head.first, head.second + 1)
                            'R' -> tail = Pair(head.first, head.second - 1)
                            'U' -> tail = Pair(head.first - 1, head.second)
                            'D' -> tail = Pair(head.first + 1, head.second)
                        }
                    }
                    visitedPositions.add(Pair(tail.first, tail.second))
                }
            }
        }
        return visitedPositions.toSet().size.toString()
    }

    override fun solvePartTwo(): String {


        return super.solvePartTwo()
    }

    private fun directNeighbours(one: Pair<Int, Int>, two: Pair<Int, Int>): Boolean {
        val oneSurroundings = mutableListOf(one)
        for (i in one.first - 1..one.first + 1) {
            for (j in one.second - 1..one.second + 1) {
                oneSurroundings.add(Pair(i, j))
            }
        }
        return oneSurroundings.contains(two)
    }
}