package mk.digital.kmpsample.ui.screens

sealed interface Screen {
    data object Home : Screen
    data object Detail : Screen
}