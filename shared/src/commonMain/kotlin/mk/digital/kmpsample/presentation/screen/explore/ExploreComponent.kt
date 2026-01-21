package mk.digital.kmpsample.presentation.screen.explore

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import mk.digital.kmpsample.domain.model.User
import mk.digital.kmpsample.domain.useCase.LoadHomeDataUseCase
import mk.digital.kmpsample.domain.useCase.TrackButtonClickUseCase
import mk.digital.kmpsample.domain.useCase.base.invoke
import mk.digital.kmpsample.presentation.base.BaseComponentContext
import mk.digital.kmpsample.presentation.base.NavEvent
import mk.digital.kmpsample.presentation.base.UseCaseRunner


class ExploreComponent : BaseComponentContext<Unit>(Unit) {
    @Composable
    override fun toolbarTitle(): String = "Explore"
    override val navIcon: ImageVector? = null
}


