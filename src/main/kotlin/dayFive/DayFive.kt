package dayFive

import AocSolution
import java.io.File
import java.util.Stack

class DayFive : AocSolution(5) {

    private val file = File("src/main/resources/inputs/dayFive/input.txt")

    private var stacks1 = readStacks()
    private var stacks2 = readStacks()
    private val instructions = readInstructions()

    private fun readStacks(): MutableList<Stack<Char>> {
        var blankLine = 0
        for ((num, line) in file.readLines().withIndex()) {
            if (line.isEmpty()) {
                blankLine = num - 1
            }
        }

        val stackNumbers = file.readLines()[blankLine].split(' ').filter { it != "" }
            .map { Pair(it.toInt(), file.readLines()[blankLine].indexOf(it)) }
        val stacks = mutableListOf<Stack<Char>>()
        for (i in stackNumbers.indices) {
            stacks.add(Stack())
        }

        for (i in blankLine downTo  0) {
            stackNumbers.forEach {
                if (file.readLines()[i].length > it.second && file.readLines()[i][it.second] != ' ') {
                    stacks[it.first - 1].push(file.readLines()[i][it.second])
                }
            }
        }
        return stacks
    }

    private fun readInstructions(): MutableList<Instruction> {
        var blankLine = 0
        for ((num, line) in file.readLines().withIndex()) {
            if (line.isEmpty()) {
                blankLine = num - 1
            }
        }

        return file.readLines().subList(blankLine + 1, file.readLines().size).map {
            it.replace("move", "").replace("from", "").replace("to", "").split(" ").filter { it.isNotBlank() }
                .map { it.toInt() }
        }.filter { it.isNotEmpty() }.map { Instruction(it[0], it[1]-1, it[2]-1) }.toMutableList()
    }

    override fun solvePartOne(): String {
        instructions.forEach {
            for(i in 0 until it.amount) {
                stacks1[it.to].push(stacks1[it.from].pop())
            }
        }

        var output = ""
        stacks1.forEach { output+= it.peek() }
        return output
    }

    override fun solvePartTwo(): String {
        val buffer = Stack<Char>()
        instructions.forEach {
            for(i in 0 until it.amount) {
                buffer.push(stacks2[it.from].pop())
            }
            for(i in 0 until buffer.size) {
                stacks2[it.to].push(buffer.pop())
            }
        }
        var output = ""
        stacks2.forEach { output+= it.peek() }
        return output
    }
}