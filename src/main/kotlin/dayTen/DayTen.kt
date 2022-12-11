package dayTen

import AocSolution
import java.io.File

class DayTen : AocSolution(10) {
    val input = readInput()
    private fun readInput(): List<String> {
        val file = File("src/main/resources/inputs/dayTen/input.txt")
        return file.readLines()
    }

    override fun solvePartOne(): String {
        val list = mutableListOf(1)
        input.forEach {
            when (it.split(" ")[0]) {
                "addx" -> {
                    list.add(list.last())
                    list.add(list.last() + it.split(" ")[1].toInt())
                }

                "noop" -> list.add(list.last())
            }
        }
        var sum = 0

        for (i in listOf(20, 60, 100, 140, 180, 220)) {
            sum += i * list[i - 1]
        }
        return sum.toString()
    }

    override fun solvePartTwo(): String {
        val list = mutableListOf(1)
        input.forEach {
            when (it.split(" ")[0]) {
                "addx" -> {
                    list.add(list.last())
                    list.add(list.last() + it.split(" ")[1].toInt())
                }

                "noop" -> list.add(list.last())
            }
        }


        var monitor = ""
        for (i in 0 until  list.size-1) {
            if(i%40 == 0) {
                monitor += "\n"
            }
            monitor += if (i%40 in listOf(list[i]-1,list[i],list[i]+1)) {
                '#'
            } else {
                '.'
            }
        }
        return monitor
    }
}