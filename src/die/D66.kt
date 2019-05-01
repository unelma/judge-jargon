package die

class D66(die1: D6, die2: D6) {

    private val value: Int = 10 * die1.value + die2.value

    init {
        require(value in 11..66) { "invalid value: $value" }
    }

    override fun equals(other: Any?): Boolean =
        other is D66 && value == other.value

    override fun hashCode(): Int = value

    override fun toString(): String = value.toString()

    companion object {

        fun roll() = D66(D6.roll(), D6.roll())
    }
}
