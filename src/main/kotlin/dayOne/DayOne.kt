package dayOne

import AocSolution
import java.io.File
import java.nio.file.Path

class DayOne : AocSolution(1) {

    val input = readInput()

    fun readInput() : MutableList<MutableList<Int>> {
        val inputs = mutableListOf<MutableList<Int>>()
        val file = File("src/main/resources/inputs/dayOne/part1_input.txt")

        inputs.add(mutableListOf())
        file.forEachLine {
            if(it.isEmpty()) {
                inputs.add(mutableListOf())
            } else {
                inputs.last().add(it.toInt())
            }
        }
        return inputs
    }

    override fun solvePartOne(): String {
        return input.map { it.sumOf { it } }.maxOf { it }.toString()
    }

    override fun solvePartTwo(): String {
        return input.map { it.sumOf { it } }.sorted().reversed().subList(0,3).sum().toString()
    }

}