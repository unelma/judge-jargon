import die.D66
import java.io.File
import java.util.concurrent.TimeUnit


fun main() {
    Speak.ENGINEERING.speakOutloud(Voice.DANIEL)
    Speak.MEDICAL.speakOutloud(Voice.SAMANTHA)
    Speak.TECH.speakOutloud(Voice.VEENA)
}

fun Speak.speakOutloud(voice: Voice = Voice.SAMANTHA) {
    val speech = this.speak(D66.roll(), D66.roll(), D66.roll(), D66.roll())
    println(speech)
    say(speech.joinToString(" "), voice)
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
