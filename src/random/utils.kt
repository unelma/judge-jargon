package random

inline fun <reified T : Enum<T>> chooseRandomly() =
    enumValues<T>().toList().shuffled().first()
