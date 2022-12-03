package dayThree

import AocSolution
import java.io.File

class DayThree : AocSolution(3){

    val input = readInput()

    private fun readInput() : MutableList<List<Char>> {
        val file = File("src/main/resources/inputs/dayThree/input.txt")
        val input = mutableListOf<List<Char>>()
        file.forEachLine {
            input.add(it.toList())
        }
        return input
    }

    override fun solvePartOne(): String {
        var sum = 0
        input.forEach {
            val left = it.subList(0, (it.size) / 2).toSet()
            val right = it.subList((it.size) / 2, it.size).toSet()

            sum += priority(left.intersect(right).last())
        }
        return sum.toString()
    }

    private fun priority(char: Char) : Int {
        return if (char.isLowerCase()) {
            char - 'a' + 1
        } else {
            char - 'A' + 27
        }
    }

    override fun solvePartTwo(): String {
        var sum = 0
        for (i in 0..input.size - 2 step 3) {
            sum += priority(input[i].toSet().intersect(input[i + 1].toSet().intersect(input[i + 2].toSet())).last())
        }
        return sum.toString()
    }

}