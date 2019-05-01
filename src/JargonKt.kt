import java.io.File
import java.util.concurrent.TimeUnit
import kotlin.math.roundToInt


fun main() {
    Speak.ENGINEERING.speakOutloud()
    Speak.MEDICAL.speakOutloud()
    Speak.TECH.speakOutloud()
}

fun Speak.speakOutloud() {
    val dices = List(4) { d66() }
    println(dices)
    val speak = this.speak(dices)
    println(speak)
    say(speak.joinToString(" "))
}

private fun d66(): D66 = 10*d6() + d6()
private fun d6(): D6 = (Math.random() * 6).roundToInt() + 1
private fun say(words: String) {
    "say $words".runCommand()
}

// From Stack Overflow: https://stackoverflow.com/a/52441962
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