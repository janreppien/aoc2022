package dayTwo

import AocSolution
import java.io.File

class DayTwo : AocSolution(2) {

    private val input = readInput()

    private fun readInput(): MutableList<List<Int>> {
        val inputs = mutableListOf<List<Int>>()
        val file = File("src/main/resources/inputs/dayTwo/input.txt")

        file.forEachLine {
            inputs.add(it.split(' ').map { signToNumber(it[0]) })
        }
        return inputs
    }

    private fun signToNumber(char: Char): Int {
        return when (char) {
            'A' -> 1
            'B' -> 2
            'C' -> 3
            'X' -> 1
            'Y' -> 2
            'Z' -> 3
            else -> 0
        }
    }

    private fun score(game: List<Int>): List<Int> {
        val input = game.toMutableList()
        if (input[0] == 1 && input[1] == 3) {
            return listOf(7,3)
        }
        if (input[0] == 3 && input[1] == 1) {
            return listOf(3,7)
        }
        if (input[0] < input[1]) {
            return listOf(input[0],input[1] + 6)
        }
        if (input[0] == input[1]) {
            return listOf(input[0] + 3, input[1] + 3)
        }
        if (input[0] > input[1]) {
            return listOf(input[0] + 6, input[1])
        }
        return listOf()
    }

    override fun solvePartOne(): String {
        var sum = 0
        input.forEach {
            sum += score(it)[1]
        }
        return sum.toString()
    }

    private fun strategy(game: List<Int>): List<Int> {
        if(game[1] == 1) {
            if(game[0] == 1) {
                return listOf(7,3)
            }
            return listOf(game[0]+6,game[0]-1)
        }

        if(game[1] == 2) {
            return listOf(game[0]+3,game[0]+3)
        }

        if(game[1] == 3) {
            if(game[0] == 3) {
                return listOf(3,7)
            }
            return listOf(game[0],game[0]+7)
        }

        return listOf()
    }

    override fun solvePartTwo(): String {
        return input.map{strategy(it)}.sumOf { it[1] }.toString()
    }
}