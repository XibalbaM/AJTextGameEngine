object Dependencies {
    object Versions {
        const val COMPOSE = "1.6.11"
        const val KOBWEB = "0.19.2"
        const val KOTLIN = "2.0.20"
        const val WRAPPERS_JS = "1.0.0-pre.684"
    }

    object Libraries {
        const val COMPOSE_HTML_CORE = "org.jetbrains.compose.html:html-core:${Versions.COMPOSE}"
        const val COMPOSE_RUNTIME = "org.jetbrains.compose.runtime:runtime:${Versions.COMPOSE}"
        const val KOBWEB_CORE = "com.varabyte.kobweb:kobweb-core:${Versions.KOBWEB}"
        const val KOBWEB_SILK = "com.varabyte.kobweb:kobweb-silk:${Versions.KOBWEB}"
        const val MDI = "com.varabyte.kobwebx:silk-icons-mdi:${Versions.KOBWEB}"
        const val KOTLINX_WRAPPERS_BROWSER = "org.jetbrains.kotlin-wrappers:kotlin-browser:${Versions.WRAPPERS_JS}"
    }

    object Plugins {
        val KOTLIN_MULTIPLATFORM = Plugin("org.jetbrains.kotlin.multiplatform", Versions.KOTLIN)
        val COMPOSE_COMPILER = Plugin("org.jetbrains.kotlin.plugin.compose", Versions.KOTLIN)
        val KOBWEB_APPLICATION = Plugin("com.varabyte.kobweb.application", Versions.KOBWEB)
    }
}

data class Plugin(val id: String, val version: String)