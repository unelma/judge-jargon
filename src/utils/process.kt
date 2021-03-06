package utils

import java.io.File
import java.util.concurrent.TimeUnit

// From https://stackoverflow.com/a/52441962
fun String.runCommand(workingDir: File = File("."),
                      timeoutAmount: Long = 60,
                      timeoutUnit: TimeUnit = TimeUnit.SECONDS) =
    ProcessBuilder(*this.split("\\s".toRegex()).toTypedArray())
        .directory(workingDir)
        .redirectOutput(ProcessBuilder.Redirect.PIPE)
        .redirectError(ProcessBuilder.Redirect.PIPE)
        .start().apply {
            waitFor(timeoutAmount, timeoutUnit)
        }.inputStream.bufferedReader().readText()
