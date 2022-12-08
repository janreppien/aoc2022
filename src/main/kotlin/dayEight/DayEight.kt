package dayEight

import AocSolution
import java.io.File

class DayEight : AocSolution(8) {

    private val input = readInput()

    private fun readInput(): MutableList<MutableList<Int>> {
        val file = File("src/main/resources/inputs/dayEight/input.txt")
        val input = mutableListOf<MutableList<Int>>()
        file.forEachLine { line ->
            input.add(mutableListOf())
            input.last().addAll(line.toList().map { char -> char.digitToInt() })
        }
        return input
    }

    override fun solvePartOne(): String {
        var visibleTrees = 0

        for (line in input.indices) {
            for (pos in input[line].indices) {
                if (isVisible(line, pos)) {
                    visibleTrees++
                }
            }
        }
        return visibleTrees.toString()
    }

    override fun solvePartTwo(): String {
        var maxScore = 0
        for (line in input.indices) {
            for (pos in input[line].indices) {
                if (isVisible(line, pos)) {
                    val newScore = calcScore(line,pos)
                    if(newScore> maxScore){
                        maxScore = newScore
                    }
                }
            }
        }
        return maxScore.toString()
    }


    private fun calcScore(x: Int, y: Int) : Int {
        if(x in listOf(input.size-1,0) || y in listOf(input[x].size -1, 0)) {
            return 0
        }

        val value = input[x][y]
        val left = input[x].subList(0, y).reversed()
        val right = input[x].subList(y + 1, input[x].size)
        val above = input.map { it[y] }.subList(0, x).reversed()
        val below = input.map { it[y] }.subList(x + 1, input.size)

        val viewLeft = if(left.max() >= value) {
            left.indexOfFirst { it >= value } + 1
        } else {
            y
        }

        val viewRight = if(right.max() >= value) {
            right.indexOfFirst { it >= value } + 1
        } else {
            input[x].size - y -1
        }

        val viewAbove = if(above.max() >= value) {
            above.indexOfFirst { it >= value } +1
        } else {
            x
        }

        val viewBelow = if(below.max() >= value) {
            below.indexOfFirst { it >= value } +1
        } else {
            input.size - x - 1
        }
        return viewLeft * viewRight * viewAbove * viewBelow
    }


    private fun isVisible(x: Int, y: Int): Boolean {

        val value = input[x][y]
        val left = input[x].subList(0, y)
        val right = input[x].subList(y + 1, input[x].size)
        val above = input.map { it[y] }.subList(0, x)
        val below = input.map { it[y] }.subList(x + 1, input.size)

        return left.isEmpty() || left.max() < value || right.isEmpty() || right.max() < value || above.isEmpty() ||
                above.max() < value || below.isEmpty() || below.max() < value
    }
}