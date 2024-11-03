import net.mamoe.yamlkt.Yaml
import net.mamoe.yamlkt.YamlList
import net.mamoe.yamlkt.YamlMap
import java.io.File

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