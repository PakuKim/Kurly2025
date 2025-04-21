import com.android.build.gradle.api.AndroidBasePlugin
import kr.co.kurly.implementations
import kr.co.kurly.ksp
import kr.co.kurly.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

class AndroidHiltConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "com.google.devtools.ksp")

            dependencies {
                ksp(
                    libs.hilt.compiler
                )
            }

            pluginManager.withPlugin("com.android.base") {
                apply(plugin = "dagger.hilt.android.plugin")
                dependencies {
                    implementations(
                        libs.hilt.android
                    )
                }
            }
        }
    }
}