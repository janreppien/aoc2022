import kotlin.system.measureNanoTime
import kotlin.system.measureTimeMillis

abstract class AocSolution(val day: Int) {

    fun solveAndPrint() {
        println("Solutions for Day ${day}:")
        val solutionPartOne: String
        val timePartOne = measureTimeMillis { solutionPartOne=solvePartOne() }
        println("Part 1: $solutionPartOne in $timePartOne ms")

        val solutionPartTwo: String
        val timePartTwo = measureTimeMillis { solutionPartTwo=solvePartTwo() }
        println("Part 1: $solutionPartTwo in $timePartTwo ms")
    }

    open fun solvePartOne(): String {
        return "not jet solved"
    }

    open fun solvePartTwo(): String {
        return "not jet solved"
    }
}