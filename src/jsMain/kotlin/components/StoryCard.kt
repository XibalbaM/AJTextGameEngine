package fr.xibalba.ajTextGameEngine.components

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.Overflow
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.navigation.RoutePrefix
import com.varabyte.kobweb.silk.components.forms.Button
import fr.xibalba.ajTextGameEngine.story.Story
import fr.xibalba.ajTextGameEngine.utils.textWrap
import fr.xibalba.ajTextGameEngine.utils.unaryPlus
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.H2

/**
 * La présentation d'une histoire pour l'écran de liste des histoires.
 */
@Composable
fun StoryCard(story: Story) {
    val ctx = rememberPageContext()
    Button(onClick = {
        ctx.router.navigateTo(RoutePrefix.prepend("/histoires/${story.id}"))
    }, Modifier.width(300.px).height(420.px).textWrap().textAlign(TextAlign.Justify)) {
        Column(verticalArrangement = Arrangement.Top, modifier = Modifier.maxHeight(400.px).overflow(Overflow.Hidden)) {
            H2 {
                +story.title
            }
            if (story.description != null) {
                Box(Modifier.height(100.percent)) {
                    +story.description
                }
            }
        }
    }
}