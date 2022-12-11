package dayNine

import AocSolution
import java.io.File


class DayNine : AocSolution(9) {

    data class Instruction(val direction: Char, val amount: Int)
    data class Rope(var parts: List<Pair<Int, Int>>)

    private val input = readInput()

    private fun readInput(): MutableList<Instruction> {
        val input = mutableListOf<Instruction>()
        val file = File("src/main/resources/inputs/dayNine/sample_input.txt")

        file.forEachLine {
            input.add(Instruction(it.split(" ")[0][0], it.split(" ")[1].toInt()))
        }
        return input
    }

    override fun solvePartOne(): String {
        var rope = listOf(Pair(0, 0), Pair(0, 0))
        val visitedPositions = mutableSetOf<Pair<Int, Int>>()
        input.forEach {
            for (i in 0 until it.amount) {
                rope = applyInstruction(rope, it)
                visitedPositions.add(rope.last())
            }
        }
        println(visitedPositions)
        printPositions(visitedPositions.toList())
        return visitedPositions.size.toString()
    }

    override fun solvePartTwo(): String {
        val tmprope = mutableListOf<Pair<Int,Int>>()
        for(i in 0 until 10) {
            tmprope.add(Pair(0,0))
        }
        var rope = tmprope.toList()
        val visitedPositions = mutableSetOf<Pair<Int, Int>>()
        input.forEach {
            for (i in 0 until it.amount) {
                rope = applyInstruction(rope, it)
                visitedPositions.add(rope.last())
            }
        }
        println(visitedPositions)
        printPositions(visitedPositions.toList())
        return visitedPositions.size.toString()
    }


    private fun printPositions(visitedPositions: List<Pair<Int, Int>>) {
        val maxHeight = visitedPositions.maxOf { it.first }
        val minHeight = visitedPositions.minOf { it.first }
        val maxWidth = visitedPositions.maxOf { it.second }
        val minWidth = visitedPositions.minOf { it.second }

        for (i in maxHeight downTo minHeight) {
            for (j in minWidth..maxWidth) {
                if (visitedPositions.contains(Pair(i, j))) {
                    print("#")
                } else {
                    print(".")
                }
            }
            println()
        }
    }

    private fun applyInstruction(rope: List<Pair<Int, Int>>, instruction: Instruction): List<Pair<Int, Int>> {
        val newRope = rope.toMutableList()
        val movement = when (instruction.direction) {
            'L' -> Pair(0, -1)
            'R' -> Pair(0, 1)
            'U' -> Pair(1, 0)
            'D' -> Pair(-1, 0)
            else -> Pair(0, 0)
        }

        newRope[0] = applyMovement(newRope[0], movement)
        for (j in 1 until rope.size) {
            if (!directNeighbours(newRope[j], newRope[j - 1])) {
                if (newRope[j - 1].first != newRope[j].first && newRope[j - 1].second != newRope[j].second) {
                    newRope[j] = (
                            when (instruction.direction) {
                                'L' -> Pair(newRope[j - 1].first, newRope[j - 1].second + 1)
                                'R' -> Pair(newRope[j - 1].first, newRope[j - 1].second - 1)
                                'U' -> Pair(newRope[j - 1].first - 1, newRope[j - 1].second)
                                'D' -> Pair(newRope[j - 1].first + 1, newRope[j - 1].second)
                                else -> Pair(1111, 1111)
                            }
                            )
                } else {
                    newRope[j] = (applyMovement(newRope[j], movement))
                }
            }
        }
        return newRope.toList()
    }

    private fun applyMovement(one: Pair<Int, Int>, two: Pair<Int, Int>): Pair<Int, Int> {
        return Pair(one.first + two.first, one.second + two.second)
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