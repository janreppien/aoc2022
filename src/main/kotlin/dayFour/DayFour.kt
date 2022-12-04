package dayFour

import AocSolution
import java.io.File
import java.util.StringJoiner

class DayFour : AocSolution(4){

    private val input = readInput()

    private fun readInput(): MutableList<Pair<Set<Int>,Set<Int>>> {
        val file = File("src/main/resources/inputs/dayFour/input.txt")
        val input = mutableListOf<Pair<Set<Int>,Set<Int>>>()
        file.forEachLine {
           val tmp = it.split(',').map { rangeToSet(it) }
            input.add(Pair(tmp[0],tmp[1]))
        }
        return input
    }

    private fun rangeToSet(input: String) : Set<Int>{
        val inputInts = input.split('-').map { it.toInt() }
        val output = mutableSetOf<Int>()
        for(i in inputInts[0]..inputInts[1]) {
            output.add(i)
        }
        return output.toSet()
    }

    override fun solvePartOne(): String {
            return input.map { if(it.first.subtract(it.second).isEmpty() || it.second.subtract(it.first).isEmpty()) 1
            else
                0}.sum().toString()
    }

    override fun solvePartTwo(): String {
        return input.map { if (it.first.intersect(it.second).isNotEmpty()) 1 else 0 }.sum().toString()
    }


}