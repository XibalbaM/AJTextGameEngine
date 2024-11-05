package fr.xibalba.ajTextGameEngine.components

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.navigation.RoutePrefix
import com.varabyte.kobweb.silk.components.forms.Button
import com.varabyte.kobweb.silk.components.forms.ButtonStyle
import com.varabyte.kobweb.silk.components.icons.mdi.MdiDarkMode
import com.varabyte.kobweb.silk.components.icons.mdi.MdiLightMode
import com.varabyte.kobweb.silk.style.addVariant
import com.varabyte.kobweb.silk.style.selectors.hover
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import com.varabyte.kobweb.silk.theme.colors.palette.Palette
import com.varabyte.kobweb.silk.theme.colors.palette.background
import com.varabyte.kobweb.silk.theme.colors.palette.toPalette
import fr.xibalba.ajTextGameEngine.utils.centered
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Text

/**
 * La hauteur du header. Une constante car elle est réutilisée pour calculer la hauteur min de la page principale.
 */
val HEADER_HEIGHT = 75.px

/**
 * Le style des boutons du header qui sont actuellement sélectionnés ou survolés.
 *
 * Dans une variable car il est utilisé à deux endroits différents.
 */
val focusedModifier: (Palette) -> Modifier = { Modifier.backgroundColor(it.background.centered(0.15f)) }

/**
 * Le style des boutons du header.
 */
val HeaderButtonStyle = ButtonStyle.addVariant {
    base {
        Modifier.size(HEADER_HEIGHT).backgroundColor(Color.transparent).borderRadius(0.px)
    }
    hover {
        focusedModifier(colorMode.toPalette())
    }
}

/**
 * Le header de l'application. Prend en paramètre la liste des liens de la barre de navigation.
 */
@Composable
fun Header(routes: Set<Pair<String, String>>) {
    var theme by ColorMode.currentState
    val ctx = rememberPageContext()
    val currentPath = routes.map { it.first }.minBy { ctx.route.path.substringAfter(it).length }
    Row(Modifier
        .backgroundColor(theme.toPalette().background.centered(0.05f))
        .position(Position.Sticky)
        .top(0.px).right(0.px).left(0.px)
        .width(100.percent).height(HEADER_HEIGHT),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            routes.forEach { (path, title) ->
                Button(onClick = { ctx.router.navigateTo(RoutePrefix.prepend(path)) },
                    if (path === currentPath) focusedModifier(theme.toPalette()) else Modifier,
                    HeaderButtonStyle) {
                    Text(title)
                }
            }
        }
        Button(onClick = { theme = theme.opposite }, Modifier.width(30.px).margin(10.px)) {
            if (theme.isLight) {
                MdiDarkMode()
            } else {
                MdiLightMode()
            }
        }
    }
}