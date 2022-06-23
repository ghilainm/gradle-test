import org.gradle.api.DefaultTask
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.tasks.*
import java.nio.file.Files
import java.nio.file.Paths
import java.util.stream.Collectors

@CacheableTask
abstract class ProduceFileTask : DefaultTask() {

    @get:OutputDirectory
    abstract val magicOutputDirectory: DirectoryProperty

    @TaskAction
    fun doLast() {
        magicOutputDirectory.get().file("output-file.txt").asFile.writeText("Hello")
    }
}