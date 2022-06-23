import org.gradle.api.Action
import org.gradle.api.Task
import org.gradle.api.file.RegularFile
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.model.ObjectFactory
import java.io.File
import javax.inject.Inject
import javax.inject.Provider


abstract class PrintInputAction @Inject constructor(private val input: Provider<RegularFile>): Action<Task> {

    @get:Inject abstract val objectFactory: ObjectFactory

    override fun execute(t: Task) {
        t.logger.info(input.get().asFile.readText())
    }

}