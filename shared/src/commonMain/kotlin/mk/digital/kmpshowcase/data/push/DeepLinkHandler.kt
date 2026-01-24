package mk.digital.kmpshowcase.data.push

import mk.digital.kmpshowcase.presentation.base.Route

object DeepLinkHandler {

    private const val SCHEME = "kmpshowcase"

    private val routeMap = mapOf(
        "" to Route.HomeSection.Home,
        "home" to Route.HomeSection.Home,
        "settings" to Route.Settings,
        "networking" to Route.HomeSection.Networking,
        "storage" to Route.HomeSection.Storage,
        "ui-components" to Route.HomeSection.UiComponents,
        "uicomponents" to Route.HomeSection.UiComponents,
        "platform-apis" to Route.HomeSection.PlatformApis,
        "platformapis" to Route.HomeSection.PlatformApis,
        "scanner" to Route.HomeSection.Scanner,
        "database" to Route.HomeSection.Database,
        "calendar" to Route.HomeSection.Calendar,
        "login" to Route.Login,
        "register" to Route.Register,
    )

    fun parseDeepLink(deepLink: String): Route? {
        if (!deepLink.startsWith("$SCHEME://")) return null
        val path = deepLink.removePrefix("$SCHEME://").lowercase()
        return routeMap[path]
    }
}
