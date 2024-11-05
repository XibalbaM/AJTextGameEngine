package fr.xibalba.ajTextGameEngine.components

import androidx.compose.runtime.Composable
import kotlinx.browser.document
import org.jetbrains.compose.web.attributes.AttrsScope
import org.jetbrains.compose.web.dom.ElementBuilder
import org.jetbrains.compose.web.dom.TagElement
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.renderComposable
import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLImageElement
import org.w3c.dom.HTMLTitleElement
import org.w3c.dom.asList

/**
 * Une fonction qui simplifie la sélection de plusieurs éléments du DOM.
 */
fun selectAll(selector: String) = document.querySelectorAll(selector).asList().unsafeCast<List<HTMLElement>>()

/**
 * Permet de changer le titre de la page.
 *
 * Elle ajoute une balise `title` dans le `head` du document et ajoute également des balises `meta` pour les réseaux sociaux.
 */
fun setTitle(title: String) = renderComposable(document.head!!) {
    selectAll("meta[property*=title]").forEach(HTMLElement::remove)
    document.querySelector("title")?.remove()

    Title(title)
    MetaProperty("og:title", title)
    MetaProperty("twitter:title", title)
}

/**
 * Une fonction qui correspond à la balise `title` du document.
 */
@Composable
fun Title(title: String) {
    TagElement<HTMLTitleElement>(
        elementBuilder = ElementBuilder.createBuilder("title"),
        applyAttrs = {},
        content = {
            Text(title)
        }
    )
}

/**
 * Une fonction qui correspond à la balise `meta` du document.
 *
 * Elle a un paramètre `attrs` qui permet de définir les attributs de la balise.
 */
@Composable
fun Meta(attrs: AttrsScope<HTMLImageElement>.() -> Unit = {}) {
    TagElement<HTMLImageElement>(
        elementBuilder = ElementBuilder.createBuilder("meta"),
        applyAttrs = {
            apply(attrs)
        },
        content = null
    )
}
@Composable
fun MetaProperty(property: String, content: String) = Meta {
    attr("property", property)
    attr("content", content)
}
@Composable
fun MetaName(name: String, content: String) = Meta {
    attr("name", name)
    attr("content", content)
}