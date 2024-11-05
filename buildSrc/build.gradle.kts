plugins {
    `kotlin-dsl` // Plugin utilisé pour un projet Kotlin visant la JVM, ce qui est le cas du code de buildSrc
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("net.mamoe.yamlkt:yamlkt:0.13.0") // Dépendance pour manipuler des fichiers YAML
}