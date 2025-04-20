package mk.digital.kmpsample.presentation.base

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.popTo
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.router.stack.replaceCurrent
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable
import mk.digital.kmpsample.presentation.screen.detail.DetailComponent
import mk.digital.kmpsample.presentation.screen.detail.DetailComponentImpl
import mk.digital.kmpsample.presentation.screen.home.HomeComponent
import mk.digital.kmpsample.presentation.screen.home.HomeComponentImpl
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

@Serializable
sealed class NavConfig {

    @Serializable
    sealed class HomeSection : NavConfig() {
        @Serializable
        data object Home : HomeSection()

        @Serializable
        data class Detail(val id: Int) : HomeSection()
    }

    @Serializable
    data object Explore : NavConfig()

    @Serializable
    data object Profile : NavConfig()
}

interface AppComponent {

    val childStack: Value<ChildStack<NavConfig, Child>>

    fun onBackClicked(toIndex: Int)

    sealed class Child {
        class Home(val component: HomeComponent) : Child()
        class Detail(val component: DetailComponent) : Child()
        data object Profile : Child()
        data object Explore : Child()
    }

    fun navigateTo(config: NavConfig)
}

class DefaultAppComponent(
    componentContext: ComponentContext
) : AppComponent, KoinComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<NavConfig>()

    override val childStack = childStack(
        source = navigation,
        serializer = NavConfig.serializer(),
        initialConfiguration = NavConfig.HomeSection.Home,
        handleBackButton = true,
        childFactory = ::createChild
    )

    override fun onBackClicked(toIndex: Int) = navigation.popTo(index = toIndex)

    private fun homeSection(context: ComponentContext, config: NavConfig.HomeSection) = when (config) {
        is NavConfig.HomeSection.Detail -> AppComponent.Child.Detail(
            DetailComponentImpl(
                componentContext = context,
                id = config.id,
                onBack = navigation::pop
            )
        )

        is NavConfig.HomeSection.Home -> AppComponent.Child.Home(
            HomeComponentImpl(
                componentContext = context,
                navigateToDetailsImpl = { navigation.push(NavConfig.HomeSection.Detail(it)) },
                get(),
                get(),
                get()
            )
        )
    }

    private fun createChild(config: NavConfig, context: ComponentContext): AppComponent.Child =
        when (config) {
            is NavConfig.HomeSection -> homeSection(context, config)
            NavConfig.Profile -> AppComponent.Child.Profile
            NavConfig.Explore -> AppComponent.Child.Explore
        }

    override fun navigateTo(config: NavConfig) {
        navigation.replaceCurrent(config)
    }
}

