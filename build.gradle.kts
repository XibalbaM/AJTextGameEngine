import com.varabyte.kobweb.gradle.application.util.configAsKobwebApplication

plugins {
    Dependencies.Plugins.KOTLIN_MULTIPLATFORM.apply {
        id(id) version version
    }
    Dependencies.Plugins.COMPOSE_COMPILER.apply {
        id(id) version version
    }
    Dependencies.Plugins.KOBWEB_APPLICATION.apply {
        id(id) version version
    }
}

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    maven("https://us-central1-maven.pkg.dev/varabyte-repos/public")
}

val generateStoriesTask = tasks.register("generateStories") {
    val file = layout.buildDirectory.file("generated/xibalba/src/jsMain/kotlin/fr/xibalba/ajTextGameEngine/data/Stories.kt").get().asFile
    outputs.dir(file.parentFile)
    inputs.dir("stories")

    doLast {
        file.parentFile.mkdirs()
        file.writeText(generateStories())
        logger.lifecycle("Generated '$file'")
    }
}

kobweb {
    app {
        export {
            includeSourceMap = false
        }
    }
}

kotlin {
    configAsKobwebApplication("app")

    js(IR)

    sourceSets {
        jsMain {
            kotlin.srcDir(generateStoriesTask)
            dependencies {
                implementation(Dependencies.Libraries.KOBWEB_CORE)
                implementation(Dependencies.Libraries.KOBWEB_SILK)
                implementation(Dependencies.Libraries.COMPOSE_HTML_CORE)
                implementation(Dependencies.Libraries.COMPOSE_RUNTIME)
                implementation(Dependencies.Libraries.MDI)
                implementation(Dependencies.Libraries.KOTLINX_WRAPPERS_BROWSER)
            }
        }
    }
}