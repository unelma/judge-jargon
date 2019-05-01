package die

class D66 private constructor(val value: Int) {
    init {
        require(value in 11..66) { "invalid value: $value" }
    }

    override fun equals(other: Any?): Boolean =
        other is D66 && value == other.value

    override fun hashCode(): Int = value

    companion object {

        fun roll() = D66(10 * D6.roll().value + D6.roll().value)
        fun fromInt(value: Int) = D66(value)
    }
}
