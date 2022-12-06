package daySix

import AocSolution
import java.io.File

class DaySix : AocSolution(6){

    val input = readInput()

    private fun readInput() : List<Char> {
        val file = File("src/main/resources/inputs/daySix/input.txt")
        return file.readLines()[0].toList()
    }

    override fun solvePartOne(): String {
        return findDistinctDigits(4).toString()
    }

    override fun solvePartTwo(): String {
        return findDistinctDigits(14).toString()
    }

    private fun findDistinctDigits(amount: Int) : Int {
        for(i in amount .. input.size+1) {
            if(input.subList(i-amount,i).toSet().size == amount) {
                return i
            }
        }
        return 0
    }

}