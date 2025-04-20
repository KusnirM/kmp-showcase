package mk.digital.kmpsample.ui.base

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.popTo
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable
import mk.digital.kmpsample.ui.screens.detail.DetailComponent
import mk.digital.kmpsample.ui.screens.detail.DetailComponentImpl
import mk.digital.kmpsample.ui.screens.home.HomeComponent
import mk.digital.kmpsample.ui.screens.home.HomeComponentImpl
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

sealed class NavConfig {
    @Serializable
    data object Home : NavConfig()

    @Serializable
    data class Detail(val id: String) : NavConfig()
}

interface AppComponent {

    val childStack: Value<ChildStack<*, Child>>

    fun onBackClicked(toIndex: Int)

    sealed class Child {
        class Home(val component: HomeComponent) : Child()
        class Detail(val component: DetailComponent) : Child()
    }
}

class DefaultAppComponent(
    componentContext: ComponentContext
) : AppComponent, KoinComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<NavConfig>()

    override val childStack = childStack(
        source = navigation,
        serializer = null,//todo:kotlin serializer, serializers?
        initialConfiguration = NavConfig.Home,
        handleBackButton = true,
        childFactory = ::createChild
    )

    override fun onBackClicked(toIndex: Int) = navigation.popTo(index = toIndex)

    private fun createChild(config: NavConfig, context: ComponentContext): AppComponent.Child =
        when (config) {
            is NavConfig.Home -> AppComponent.Child.Home(
                HomeComponentImpl(
                    componentContext = context,
                    loadHomeDataUseCase = get(),
                    trackButtonClickUseCase = get(),
                    navigateToDetailsImpl = {
                        navigation.push(NavConfig.Detail(it))//todo extract navigator
                    }
                ),
            )

            is NavConfig.Detail -> AppComponent.Child.Detail(
                DetailComponentImpl(
                    componentContext = context,
                    id = config.id,
                    onBack = {
                        println("back")
                        navigation.pop()
                    }
                )
            )
        }
}
