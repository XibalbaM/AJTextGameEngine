package fr.xibalba.ajTextGameEngine.utils

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.dom.Text

fun String.kebabCaseToNaturalCase(): String {
    return this.split("-").joinToString(" ") { it.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() } }
}

@Composable
operator fun String.unaryPlus() = Text(this)