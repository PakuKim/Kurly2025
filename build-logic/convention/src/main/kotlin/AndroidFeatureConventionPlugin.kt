import kr.co.kurly.implementations
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "kurly.android.library")
            apply(plugin = "kurly.android.hilt")
            apply(plugin = "org.jetbrains.kotlin.plugin.serialization")

            dependencies {
                implementations(
                    project(":core:ui"),
                    project(":core:common"),
                    project(":core:domain"),
                )
            }
        }
    }
}