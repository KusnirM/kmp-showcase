package mk.digital.kmpshowcase.presentation.base

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface Navigation : NavKey {

    @Serializable
    sealed interface HomeSection : Navigation {
        @Serializable
        data object Home : HomeSection

        @Serializable
        data object UiComponents : HomeSection

        @Serializable
        data object Networking : HomeSection

        @Serializable
        data object Storage : HomeSection

        @Serializable
        data object PlatformApis : HomeSection
    }

    @Serializable
    data object Explore : Navigation

    @Serializable
    data object Profile : Navigation
}
