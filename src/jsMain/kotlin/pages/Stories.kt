package fr.xibalba.ajTextGameEngine.pages

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.style.breakpoint.ResponsiveValues
import fr.xibalba.ajTextGameEngine.components.StoryCard
import fr.xibalba.ajTextGameEngine.data.stories
import fr.xibalba.ajTextGameEngine.layouts.PageLayout
import fr.xibalba.ajTextGameEngine.utils.unaryPlus
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.H1

/**
 * La page de liste des histoires. J'utilise une grille pour afficher la liste, avec un nombre de colonnes qui dépend de la taille de l'écran.
 */
@Page("/histoires")
@Composable
fun Stories() {
    PageLayout("Histoires") {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            H1 {
                +"Histoires"
            }
            SimpleGrid(ResponsiveValues(1, 1, 2, 3, 3), Modifier.gap(10.px)) {
                for (story in stories) {
                    StoryCard(story)
                }
            }
        }
    }
}