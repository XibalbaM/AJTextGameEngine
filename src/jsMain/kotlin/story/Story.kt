package fr.xibalba.ajTextGameEngine.story

data class Story(val id: String, val title: String, val description: String?, val graph: Graph<Chapter, String>)

data class Chapter(val title: String, val text: String)
