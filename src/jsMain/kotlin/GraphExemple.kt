package fr.xibalba.ajTextGameEngine

import fr.xibalba.ajTextGameEngine.story.Graph

fun createExempleGraph() {
    Graph.build<String, String> {
        addVertex(0, "A")
        addVertex(1, "B")
        addVertex(2, "C")

        addEdge(0, 1, "A -> B")
        addEdge(1, 2, "B -> C")
    }
}