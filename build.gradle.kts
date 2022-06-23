val producerTask = tasks.register<ProducerTask>("produce")
val consumerTask = tasks.register<ConsumerTask>("consume")

val magicOutputProvider: Provider<RegularFile> = producerTask.flatMap { it.magicOutput }
val printInputAction = project.objects.newInstance(PrintInputAction::class.java, magicOutputProvider)

consumerTask.configure {
    magicInput.set(magicOutputProvider)
    magicOutput.set(project.layout.buildDirectory.file("consumerOutput.txt"))
    doLast(printInputAction)
}

producerTask.configure {
    magicInput.set(project.layout.projectDirectory.file("input.txt"))
    magicOutput.set(project.layout.buildDirectory.file("producerOutput.txt"))
}
