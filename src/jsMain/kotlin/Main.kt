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