import net.mamoe.yamlkt.Yaml
import net.mamoe.yamlkt.YamlList
import net.mamoe.yamlkt.YamlMap
import java.io.File

/**
 * Génère le code pour initialiser la liste des histoires à partir des fichiers YAML des histoires.
 *
 * J'en fait une variable pour pouvoir l'utiliser partout facilement.
 *
 * J'ai choisis de compiler les histoires dans le code pour éviter de faire des centaines de requète. Là, tout est inclus dans le bundle généré par kobweb.
 */
fun generateStories(): String {
    val storiesDir = File("stories")
    val stories = storiesDir.listFiles() ?: emptyArray()
    val storiesText = stories.joinToString(",\n") { storyDir ->
        val infoFile = File(storyDir, "info.yaml")
        val info = Yaml.decodeYamlMapFromString(infoFile.readText())
        val title = info.getString("title")
        val description = info.getStringOrNull("description")
        val chaptersDir = File(storyDir, "chapters")
        val graph = createGraph(chaptersDir)
        "Story(\"${storyDir.name}\", \"$title\", \"$description\", $graph)"
    }
    return """
        package fr.xibalba.ajTextGameEngine.data
        import fr.xibalba.ajTextGameEngine.story.Chapter
        import fr.xibalba.ajTextGameEngine.story.Story
        import fr.xibalba.ajTextGameEngine.story.Graph
        val stories = listOf(
            $storiesText
        )
    """.trimIndent()
}

/**
 * Génère le code pour créer un graphe à partir des fichiers YAML des chapitres.
 * Pour chaque fichier YAML, on crée un sommet avec le titre et le texte du chapitre.
 * On crée ensuite une arête pour chaque choix du chapitre.
 * pathList est utilisé pour associer un identifiant unique à chaque chemin de chapitre.
 *
 * Voir GraphExemple.kt dans src/jsMain/kotlin pour un exemple de création de graphe.
 */
fun createGraph(chaptersDir: File): String {
    val vertices = mutableListOf<String>()
    val edges = mutableListOf<String>()
    val pathList = mutableSetOf<String>("index")
    chaptersDir.listPathsRecursive().forEach {
        val data = Yaml.decodeYamlMapFromString(File(chaptersDir, it).readText())
        val path = it.removeSuffix(".yaml")
        val parentPath = if (path.contains("/")) path.substringBeforeLast("/") else ""
        if (!pathList.contains(path)) {
            pathList.add(path)
        }
        val id = pathList.indexOf(path)
        vertices.add("addVertex($id, Chapter(\"${data.getString("title")}\", \"${data.getString("text").replace("\n", "\\n").replace("\"", "\\\"")}\"))")

        val choices = data.get("choices") as YamlList?
        choices?.forEach { choice ->
            val targetPath = processPath((choice as YamlMap).getString("chapter"), parentPath).removeSuffix(".yaml")
            if (!pathList.contains(targetPath)) {
                pathList.add(targetPath)
            }
            val targetId = pathList.indexOf(targetPath)
            val text = choice.getString("text")
            edges.add("addEdge($id, $targetId, \"$text\")")
        }
    }
    return "Graph.build {\n${vertices.joinToString("\n")}\n${edges.joinToString("\n")}\n}"
}

/**
 * Une fonction pour normaliser les chemins qui peuvent être utilisés pour référencer le chapitre suivant.
 *
 * Si le chemin commence par un `/`, il est considéré comme absolu et est relatif à la racine du dossier des chapitres.
 * Si le chemin relatif, on est à la racine du dossier des chapitres. Le chemin est alors retourné tel quel.
 * Si le chemin commence par `./`, il est relatif au dossier du chapitre actuel.
 * Si le chemin commence par `../`, il est relatif au dossier parent du chapitre actuel.
 * Sinon, le chemin est relatif au dossier du chapitre actuel.
 */
fun processPath(path: String, currentPath: String): String {
    when {
        path.startsWith("/") -> return path.removePrefix("/")
        currentPath.isEmpty() -> return path
        path.startsWith("./") -> return currentPath.removeSuffix("/") + path.removePrefix(".")
        path.startsWith("../") -> {
            val parentPath = currentPath.removeSuffix("/").let { if (it.contains("/")) it.substringBeforeLast("/") else "" }
            return processPath(path.removePrefix("../"), parentPath)
        }
        else -> return currentPath.removeSuffix("/") + "/" + path
    }
}