import java.io.File

/**
 * Une fonction qui liste récursivement le contenu d'un dossier.
 */
fun File.listPathsRecursive(): List<String> {
    if (!exists() || !isDirectory) return emptyList()
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