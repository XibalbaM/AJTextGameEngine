package fr.xibalba.ajTextGameEngine.utils

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.dom.Text

/**
 * Un raccourci pour créer un [Text] à partir d'une [String] plus lisiblement (+"text" au lieu de Text("text"))
 */
@Composable
operator fun String.unaryPlus() = Text(this)