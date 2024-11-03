package fr.xibalba.ajTextGameEngine.layouts

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.minHeight
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.base
import com.varabyte.kobweb.silk.style.toAttrs
import fr.xibalba.ajTextGameEngine.components.HEADER_HEIGHT
import fr.xibalba.ajTextGameEngine.components.Header
import fr.xibalba.ajTextGameEngine.components.setTitle
import fr.xibalba.ajTextGameEngine.utils.marginXAuto
import org.jetbrains.compose.web.css.minus
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.vh
import org.jetbrains.compose.web.dom.Main
import web.window.window

val MainTheme = CssStyle.base {
    Modifier
        .margin {
            top(HEADER_HEIGHT)
        }
        .marginXAuto()
        .width(98.percent)
        .minHeight(100.vh - HEADER_HEIGHT)
}

@Composable
fun PageLayout(title: String, content: @Composable () -> Unit) {
    setTitle(title)

    Header(routes = setOf(
        "/" to "Accueil",
        "/histoires" to "Histoires",
        "/creer" to "Créer",
        "https://github.com/XibalbaM/AJTextGameEngine" to "Github"
    ))
    Main(MainTheme.toAttrs()) {
        content()
    }

    window.scroll(0.0, 0.0)
}