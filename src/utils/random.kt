package utils

import kotlin.math.roundToInt

inline fun <reified T : Enum<T>> selectRandomly() =
    enumValues<T>().toList().shuffled().first()

fun IntRange.selectRandomly() =
    (Math.random() * (endInclusive - start)).roundToInt() + start