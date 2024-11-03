package fr.xibalba.ajTextGameEngine.pages

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.gap
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.navigation.RoutePrefix
import com.varabyte.kobweb.silk.components.forms.Button
import fr.xibalba.ajTextGameEngine.data.stories
import fr.xibalba.ajTextGameEngine.layouts.PageLayout
import fr.xibalba.ajTextGameEngine.utils.unaryPlus
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.Text
import web.svg.SVG.path

@Page("/histoires/{id}/{chapter}")
@Composable
fun Chapter() {
    val ctx = rememberPageContext()
    val id = ctx.route.params["id"] ?: error("Missing id")
    val chapterId = ctx.route.params["chapter"] ?: error("Missing chapter")
    val story = stories.find { it.id == id } ?: error("Story not found")
    val chapterVertex = if (chapterId === "index") story.graph.vertices.find { it.id == 0 }!! else story.graph.vertices.find { it.id == chapterId.toInt() } ?: error("Chapter not found")
    val edges = story.graph.edges.filter { it.from == chapterVertex.id }
    PageLayout("${chapterVertex.data.title} - ${story.title}") {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            H1 {
                +chapterVertex.data.title
            }

            Text(chapterVertex.data.text)

            if (edges.isNotEmpty()) {
                Row(Modifier.gap(10.px)) {
                    for (edge in edges) {
                        Button(onClick = {
                            ctx.router.navigateTo(RoutePrefix.prepend("/histoires/${story.id}/${edge.to}"))
                        }) {
                            +edge.data
                        }
                    }
                }
            } else {
                H2 {
                    +"Fin de l'histoire !"
                }
                Row(Modifier.gap(10.px)) {
                    Button(onClick = {
                        ctx.router.navigateTo(RoutePrefix.prepend("/histoires"))
                    }) {
                        +"Retour à la liste"
                    }

                    Button(onClick = {
                        ctx.router.navigateTo(RoutePrefix.prepend("/histoires/${story.id}"))
                    }) {
                        +"Retour au début"
                    }
                }
            }
        }
    }
}