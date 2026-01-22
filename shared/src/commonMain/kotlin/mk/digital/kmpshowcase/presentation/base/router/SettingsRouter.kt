package mk.digital.kmpshowcase.presentation.base.router

interface SettingsRouter {
    fun openSettings()
}

expect class SettingsRouterImpl : SettingsRouter {
    override fun openSettings()
}
