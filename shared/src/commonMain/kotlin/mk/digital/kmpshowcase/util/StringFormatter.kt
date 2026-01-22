package mk.digital.kmpshowcase.util

expect object StringFormatter {
    fun formatDouble(value: Double, decimals: Int): String
}
