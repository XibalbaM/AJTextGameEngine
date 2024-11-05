package fr.xibalba.ajTextGameEngine.story

/**
 * Une histoire est composée d'un graphe de chapitres.
 *
 * Les sommets du graphe contiennent les chapitres et les arêtes contiennent le texte du choix qui mène à ce chapitre.
 */
data class Story(val id: String, val title: String, val description: String?, val graph: Graph<Chapter, String>)

/**
 * Un chapitre.
 */
data class Chapter(val title: String, val text: String)
