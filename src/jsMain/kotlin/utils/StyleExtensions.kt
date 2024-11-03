package fr.xibalba.ajTextGameEngine.utils

import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.graphics.Color.Companion.DEFAULT_SHIFTING_PERCENT
import com.varabyte.kobweb.compose.ui.graphics.lightened
import com.varabyte.kobweb.compose.ui.graphics.luminance
import com.varabyte.kobweb.compose.ui.styleModifier
import org.jetbrains.compose.web.css.keywords.auto

fun Color.centered(byPercent: Float = DEFAULT_SHIFTING_PERCENT) = if (this.luminance > 0.5) this.darkened(byPercent) else this.lightened(byPercent)

fun Color.extremized(byPercent: Float = DEFAULT_SHIFTING_PERCENT) = if (this.luminance > 0.5) this.lightened(byPercent) else this.darkened(byPercent)

fun Modifier.marginXAuto() = this.styleModifier {
    property("margin-left", auto)
    property("margin-right", auto)
}

fun Modifier.textWrap() = this.styleModifier {
    property("text-wrap", "wrap")
}