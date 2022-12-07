package daySeven

import AocSolution
import java.io.File

class DaySeven : AocSolution(7) {

    private val input = readInput()

    /**
     * I am not pound of this
     */
    private fun readInput(): RootDirectory {
        val file = File("src/main/resources/inputs/daySeven/input.txt")
        val root = RootDirectory("/")
        var currentDirectory = root as Directory
        var readingContent = false

        file.readLines().forEach {
            if (readingContent) {
                if (it[0] == '$') {
                    readingContent = false
                } else {
                    if (it.contains("dir")) {
                        currentDirectory.content.add(InnerDirectory(it.replace("dir ", ""), currentDirectory))
                    } else if (it == "$ cd ..") {
                        currentDirectory = (currentDirectory as InnerDirectory).parent
                    } else {
                        val size = it.substringBefore(" ").toInt()
                        val name = it.split(" ")[1].split(".")[0]
                        val extension: String = if (it.contains(".")) {
                            it.split(" ")[1].split(".")[1]
                        } else {
                            ""
                        }
                        currentDirectory.content.add(File(name, extension, size))
                    }
                }
            }
            if (it[0] == '$') {
                if (it.subSequence(2, 4) == "cd") {
                    if (it.replace("$ cd ", "").contains("/")) {
                        currentDirectory = root
                    } else if (it == "$ cd ..") {
                        currentDirectory = (currentDirectory as InnerDirectory).parent
                    } else {
                        currentDirectory = currentDirectory.getDirectories().filter { dir ->
                            dir.name.contains(
                                it
                                    .replace("$ cd ", "")
                            )
                        }[0]
                    }
                } else if (it.subSequence(2, 4) == "ls") {
                    readingContent = true
                }
            }

        }
        return root
    }

    override fun solvePartOne(): String {
        return findDirectoriesSmallerThen(input, mutableSetOf(), 100000).sumOf { it.getSize() }.toString()
    }

    override fun solvePartTwo(): String {
        val neededSpace = 30000000 - (70000000 - input.getSize())
        return findDirectoriesLargerThen(input, mutableSetOf(), neededSpace).minOf { it.getSize() }.toString()
    }

    private fun findDirectoriesSmallerThen(start: Directory, directories: MutableSet<Directory>, minSize: Int):
            MutableSet<Directory> {
        if (start.getSize() <= minSize) {
            directories.add(start)
        }
        start.getDirectories().forEach {
            directories.addAll(findDirectoriesSmallerThen(it, directories, minSize))
        }
        return directories
    }

    private fun findDirectoriesLargerThen(start: Directory, directories: MutableSet<Directory>, minSize: Int):
            MutableSet<Directory> {
        if (start.getSize() >= minSize) {
            directories.add(start)
        }
        start.getDirectories().forEach {
            directories.addAll(findDirectoriesLargerThen(it, directories, minSize))
        }
        return directories
    }
}