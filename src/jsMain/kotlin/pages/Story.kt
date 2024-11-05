package fr.xibalba.ajTextGameEngine.pages

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.navigation.RoutePrefix
import com.varabyte.kobweb.silk.components.forms.Button
import fr.xibalba.ajTextGameEngine.data.stories
import fr.xibalba.ajTextGameEngine.layouts.PageLayout
import fr.xibalba.ajTextGameEngine.utils.unaryPlus
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.Text

/**
 * La page de présentation d'une histoire.
 */
@Page("/histoires/{id}")
@Composable
fun Story() {
    val ctx = rememberPageContext()
    val id = ctx.route.params["id"] ?: error("Missing id")
    val story = stories.find { it.id == id } ?: error("Story not found")

    PageLayout(story.title) {
        Column {
            H1 {
                +story.title
            }

            Text(story.description ?: "")

            Row {
                Button(onClick = {
                    ctx.router.navigateTo(RoutePrefix.prepend("/histoires"))
                }) {
                    +"Retour"
                }

                Button(onClick = {
                    ctx.router.navigateTo(RoutePrefix.prepend("/histoires/${story.id}/index"))
                }) {
                    +"Commencer"
                }
            }
        }
    }
}