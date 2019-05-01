package die

import utils.selectRandomly

class D6 private constructor(val value: Int) {
    init {
        require(value in 1..6) {
            "invalid value: $value"
        }
    }

    override fun equals(other: Any?): Boolean =
        other is D6 && value == other.value

    override fun hashCode(): Int = value

    override fun toString(): String = value.toString()

    companion object {
        fun roll() = D6((1..6).selectRandomly())
        fun fromInt(value: Int) = D6(value)
    }
}
