package fr.xibalba.ajTextGameEngine

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.compose.ui.modifiers.minHeight
import com.varabyte.kobweb.core.App
import com.varabyte.kobweb.silk.SilkApp
import com.varabyte.kobweb.silk.components.layout.Surface
import com.varabyte.kobweb.silk.style.common.SmoothColorStyle
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import kotlinx.browser.window
import org.jetbrains.compose.web.css.vh
import web.storage.localStorage

/**
 * Le point d'entrée de l'application
 *
 * Il s'occupe de récupérer le thème actuel de l'utilisateur et de le stocker en local storage quand il change
 *
 * Surface(SmoothColorStyle.toModifier().minHeight(100.vh)) permet de rendre le changement de thème plus fluide
 *
 * SilkApp est le point d'entrée de l'application Kobweb
 *
 * Il faut savoir qu'avec compose multiplateforme, un composant est réaffiché quand une variable dynamique qu'il utilise change. Ici, quand le theme change, le code dans SkilApp est réexécuté pour réafficher la page. Comme la sauvegarde dans le localstorage est dedans, elle est fait à chaque changement de thème.
 */
@App
@Composable
fun AppEntry(content: @Composable () -> Unit) {
    var theme by ColorMode.currentState
    theme = getTheme()
    SilkApp {
        localStorage.setItem("theme", if (theme.isLight) "light" else "dark")
        Surface(SmoothColorStyle.toModifier().minHeight(100.vh)) {
            content()
        }
    }
}

/**
 * Récupère le thème actuel de l'utilisateur
 *
 * Si le thème est stocké en local storage, on le récupère
 * Sinon, on regarde si le thème préféré de l'utilisateur est le thème sombre
 * Sinon, on utilise le thème clair
 */
fun getTheme(): ColorMode {
    return if (localStorage.getItem("theme") != null) {
        if (localStorage.getItem("theme") == "dark") {
            ColorMode.DARK
        } else {
            ColorMode.LIGHT
        }
    } else if (window.matchMedia("(prefers-color-scheme: dark)").matches) {
        ColorMode.DARK
    } else {
        ColorMode.LIGHT
    }
}