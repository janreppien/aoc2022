import dayFive.DayFive
import dayFour.DayFour
import dayOne.DayOne
import daySeven.DaySeven
import daySix.DaySix
import dayThree.DayThree
import dayTwo.DayTwo
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    val solution : MutableList<AocSolution>
    var timeWithReadAndPrint = measureTimeMillis {
        solution = mutableListOf(DayOne(), DayTwo(), DayThree(), DayFour(), DayFive(), DaySix(), DaySeven());
    }

    if(args.contains("-t")) {
        solution.last().solveAndPrint()
    } else {
        var totalTime: Long = 0
        timeWithReadAndPrint += measureTimeMillis {
            solution.forEach { totalTime += it.solveAndPrint() }
        }
        println("Total Time: $totalTime ms")
        println("Total Time with print and read: $timeWithReadAndPrint ms")

    }

}