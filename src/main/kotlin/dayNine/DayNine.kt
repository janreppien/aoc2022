package dayNine

import AocSolution
import java.io.File


class DayNine : AocSolution(9) {

    data class Instruction(val direction: Char, val amount: Int)

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
            val results = applyInstruction(rope, it, listOf())
            visitedPositions.addAll(results.second)
            rope = results.first
            println(it)
            //println("visited Positions: $visitedPositions")
            printPositions(visitedPositions.toList())
        }
        //println(visitedPositions)
        return visitedPositions.size.toString()
    }

    override fun solvePartTwo(): String {


        return super.solvePartTwo()
    }


    private fun printPositions(visitedPositions: List<Pair<Int, Int>>) {
        val maxHeight = visitedPositions.maxOf { it.first }
        val minHeight = visitedPositions.minOf { it.first }
        val maxWidth = visitedPositions.maxOf { it.second }
        val minWidth = visitedPositions.minOf { it.second }

        for(i in maxHeight downTo minHeight) {
            for(j in minWidth .. maxWidth) {
                if(visitedPositions.contains(Pair(i,j))) {
                    print("#")
                }else {
                    print(".")
                }
            }
            println()
        }
    }

    private fun applyInstruction(
            rope: List<Pair<Int, Int>>, instruction: Instruction, visitedPositions:
            List<Pair<Int, Int>>
    ): Pair<List<Pair<Int, Int>>, List<Pair<Int, Int>>> {
        //println(visitedPositions)
        if (instruction.amount == 0) {
            return Pair(rope, visitedPositions)
        }

        val newRope = mutableListOf<Pair<Int, Int>>()
        val direction =
                when (instruction.direction) {
                    'L' -> Pair(0, -1)
                    'R' -> Pair(0, 1)
                    'D' -> Pair(-1, 0)
                    'U' -> Pair(1, 0)
                    else -> Pair(0, 0)
                }
        newRope.add(Pair(rope.first().first + direction.first, rope.first().second + direction.second))
        for (i in 1 until   rope.size) {
            if (!directNeighbours(newRope.last(), rope[i])) {
                if (instruction.direction in listOf('L', 'R')) {
                    if (rope[i].first == newRope[i - 1].first) {
                        newRope.add(Pair(rope[i].first + direction.first, rope[i].second + direction.second))
                    } else {
                        println("jump LR")
                        newRope.add(
                                when (instruction.direction) {
                                    'L' -> Pair(newRope.last().first, newRope.last().second + 1)
                                    'R' -> Pair(newRope.last().first, newRope.last().second - 1)
                                    else -> Pair(-1111, -1111)
                                }
                        )
                    }
                } else {
                    if (rope[i].second == newRope.last().second) {
                        newRope.add(Pair(rope[i].first + direction.first, rope[i].second + direction.second))
                    } else {
                        println("jump UD")
                        newRope.add(
                                when (instruction.direction) {
                                    'U' -> Pair(newRope.last().first - 1, newRope.last().second)
                                    'D' -> Pair(newRope.last().first + 1, newRope.last().second)
                                    else -> Pair(-1111, -1111)
                                }
                        )
                    }
                }
            } else {
                newRope.add(rope[i])
            }
        }
        return applyInstruction(
                newRope, Instruction(instruction.direction, instruction.amount - 1), visitedPositions
                + newRope.last()
        )
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