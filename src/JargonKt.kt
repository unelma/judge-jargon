import die.D66
import utils.runCommand
import utils.selectRandomly


fun main() {
    repeat(1000) {
        speakOutloud(speeching = selectRandomly(), voice = selectRandomly())
        val waitSeconds = (3..6).selectRandomly()
        println("... $waitSeconds seconds till next talk.")
        Thread.sleep(1000L * waitSeconds)
    }
}

fun speakOutloud(speeching: Speeching, voice: Voice = Voice.SAMANTHA) {
    val speech = speeching.speak(D66.roll(), D66.roll(), D66.roll(), D66.roll())
    print("$voice talks $speeching: $speech".toLowerCase())
    say(speech, voice)
}

private fun say(words: String, voice: Voice) {
    "say $words -v $voice".runCommand()
}
