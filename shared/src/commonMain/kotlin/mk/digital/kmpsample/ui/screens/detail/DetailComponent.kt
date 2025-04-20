package mk.digital.kmpsample.ui.screens.detail

import com.arkivanov.decompose.ComponentContext


interface DetailComponent {
    val id: String
    val onBack: () -> Unit
}

class DetailComponentImpl(
    componentContext: ComponentContext,
    override val id: String,
    override val onBack: () -> Unit
) : DetailComponent, ComponentContext by componentContext
