import die.D66
import random.chooseRandomly
import java.io.File
import java.util.concurrent.TimeUnit


fun main() {
    repeat(1000) {
        chooseRandomly<Speak>().speakOutloud(voice = chooseRandomly())
        val waitTime = (Math.random() * 10000).toLong()
        print("... ${waitTime/1000} seconds till next talk.")
        Thread.sleep(waitTime)
        println()
    }
}

fun Speak.speakOutloud(voice: Voice = Voice.SAMANTHA) {
    val speech = this.speak(D66.roll(), D66.roll(), D66.roll(), D66.roll())
    val sentence = speech.joinToString(" ")
    print("$voice talks $this: $sentence".toLowerCase())
    say(sentence, voice)
}

private fun say(words: String, voice: Voice) {
    "say $words -v $voice".runCommand()
}

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
