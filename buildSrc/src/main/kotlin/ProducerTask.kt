import org.gradle.api.DefaultTask
import org.gradle.api.file.ProjectLayout
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.tasks.*
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.util.stream.Collectors
import javax.inject.Inject

@CacheableTask
public abstract class ProducerTask : DefaultTask() {

    @get:InputFile
    @get:PathSensitive(PathSensitivity.RELATIVE)
    abstract val magicInput: RegularFileProperty

    @get:OutputFile
    abstract val magicOutput: RegularFileProperty

    @TaskAction
    fun doLast() {
        val magicInput = magicInput.get().asFile
        this.logger.info(magicInput.readText())
        val magicOutputContent = magicInput.readText() + " and stuff..."
        magicOutput.get().asFile.writeText(magicOutputContent)
    }
}
