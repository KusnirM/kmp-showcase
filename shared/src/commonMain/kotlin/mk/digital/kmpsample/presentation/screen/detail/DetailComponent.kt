package mk.digital.kmpsample.presentation.screen.detail

import com.arkivanov.decompose.ComponentContext


interface DetailComponent {
    val id: Int
    val onBack: () -> Unit
}

class DetailComponentImpl(
    componentContext: ComponentContext,
    override val id: Int,
    override val onBack: () -> Unit
) : DetailComponent, ComponentContext by componentContext
