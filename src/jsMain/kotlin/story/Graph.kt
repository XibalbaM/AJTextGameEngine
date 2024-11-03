package fr.xibalba.ajTextGameEngine.story

data class Vertex<T : Any?>(val id: Int, val data: T)

data class Edge<T>(val from: Int, val to: Int, val data: T)

data class Graph<T, U>(val vertices: Set<Vertex<T>>, val edges: Set<Edge<U>>) {
    fun getEdges(from: Vertex<T>): Set<Edge<U>> {
        return edges.filter { it.from == from.id }.toSet()
    }

    companion object {
        fun <T, U> build(builder: GraphBuilder<T, U>.() -> Unit): Graph<T, U> {
            val graphBuilder = GraphBuilder<T, U>()
            graphBuilder.builder()
            return graphBuilder.build()
        }
    }
}

class GraphBuilder<T, U> {
    private val vertices = mutableSetOf<Vertex<T>>()
    private val edges = mutableSetOf<Edge<U>>()

    fun addVertex(id: Int, data: T): Vertex<T> {
        if (vertices.any { it.id == id }) {
            error("Vertex $id already exists")
        }
        val vertex = Vertex(id, data)
        vertices.add(vertex)
        return vertex
    }

    fun addEdge(from: Int, to: Int, data: U) {
        if (vertices.none { it.id == from }) {
            error("Vertex $from not found")
        }
        if (vertices.none { it.id == to }) {
            error("Vertex $to not found")
        }
        edges.add(Edge(from, to, data))
    }

    fun build(): Graph<T, U> {
        return Graph(vertices, edges)
    }
}