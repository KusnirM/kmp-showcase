import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.jvm.toolchain.JavaLanguageVersion
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile


fun Project.configureCompilerOptions() {
    tasks.withType(KotlinJvmCompile::class.java).configureEach {
        it.compilerOptions.jvmTarget.set(JvmTarget.JVM_17)
    }

    extensions.getByName("java").apply {
        this as JavaPluginExtension
        toolchain.languageVersion.set(JavaLanguageVersion.of(17))
    }
}
