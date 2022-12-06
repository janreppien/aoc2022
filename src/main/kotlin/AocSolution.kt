import kotlin.system.measureTimeMillis

abstract class AocSolution(private val day: Int) {

    fun solveAndPrint():Long {
        println("Solutions for Day $day")
        val solutionPartOne: String
        val timePartOne = measureTimeMillis { solutionPartOne=solvePartOne() }
        println("Part 1: $solutionPartOne in $timePartOne ms")

        val solutionPartTwo: String
        val timePartTwo = measureTimeMillis { solutionPartTwo=solvePartTwo() }
        println("Part 2: $solutionPartTwo in $timePartTwo ms\n")
        return timePartOne+timePartTwo
    }

    open fun solvePartOne(): String {
        return "not jet solved"
    }

    open fun solvePartTwo(): String {
        return "not jet solved"
    }
}