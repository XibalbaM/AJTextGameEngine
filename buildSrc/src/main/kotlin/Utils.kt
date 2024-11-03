import java.io.File

fun File.listPathsRecursive(): List<String> {
    val paths = mutableListOf<String>()
    val files = listFiles() ?: return emptyList()
    for (file in files) {
        if (file.isDirectory) {
            paths.addAll(file.listPathsRecursive().map { "${file.name}/$it" })
        } else {
            paths.add(file.name)
        }
    }
    return paths
}