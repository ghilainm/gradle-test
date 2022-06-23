import org.gradle.api.DefaultTask
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.tasks.*
import java.nio.file.Files
import java.nio.file.Paths
import java.util.stream.Collectors

@CacheableTask
abstract class ConsumerTask : DefaultTask() {

    @get:InputFile
    @get:PathSensitive(PathSensitivity.RELATIVE)
    abstract val magicInput: RegularFileProperty

    @get:OutputFile
    abstract val magicOutput: RegularFileProperty

    @TaskAction
    fun doLast() {
        magicOutput.get().asFile.writeText(magicInput.get().asFile.readText() + " and more stuff")
    }
}