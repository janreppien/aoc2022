package daySeven

open class Directory(val name: String) : Node() {
    var content = mutableListOf<Node>()

    fun getFiles() : List<File>{
        return content.filterIsInstance<File>()
    }

    fun getDirectories() : List<Directory> {
        return content.filterIsInstance<Directory>()
    }

    fun getSize() : Int {
        //println("$name: ${recSize(this)}")
        return recSize(this)
    }

    fun getFileSize() : Int {
        return getFiles().sumOf { it.size }
    }

    private fun recSize(dir: Directory) :Int {
        if(content.filterIsInstance<Directory>().isEmpty()) {
            return dir.getFileSize()
        } else {
            return dir.getFileSize() + dir.content.filterIsInstance<Directory>().sumOf { it.recSize(it) }
        }
    }

    fun containsDir(name: String) : Boolean {
        return content.filterIsInstance<Directory>().map { it.name }.contains(name)
    }
}