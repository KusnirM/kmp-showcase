package mk.digital.kmpsample.presentation.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import mk.digital.kmpsample.domain.model.User
import mk.digital.kmpsample.getPlatformName
import mk.digital.kmpsample.presentation.component.LoadingView
import mk.digital.kmpsample.presentation.component.TopAppBar
import mk.digital.kmpsample.presentation.component.cards.AppElevatedCard
import mk.digital.kmpsample.presentation.component.spacers.ColumnSpacer.Spacer4
import mk.digital.kmpsample.presentation.component.spacers.ColumnSpacer.Spacer8
import mk.digital.kmpsample.presentation.component.text.bodyLarge.TextBodyLargeNeutral80
import mk.digital.kmpsample.presentation.component.text.bodyMedium.TextBodyMediumNeutral80
import mk.digital.kmpsample.presentation.foundation.space4

@Composable
fun HomeScreen(component: HomeComponent) {
    val state by component.state.collectAsState()
    Column {
        TopAppBar(title = "Home from ${getPlatformName()}", backIcon = null)

        if (state.loading) {
            LoadingView()
        } else {
            Column(Modifier.verticalScroll(rememberScrollState()).padding(space4)) {
                Spacer4()
                TextBodyLargeNeutral80("users")
                Spacer4()
                state.users.forEach {
                    Spacer4()
                    UserCard(it) {
                        component.navigateToDetails(it.id)
                    }
                }
                Spacer8()
            }
        }
    }
}

@Composable
private fun UserCard(user: User, onClick: () -> Unit) {
    AppElevatedCard(Modifier.fillMaxWidth().clickable(onClick = onClick).padding(space4)) {
        TextBodyLargeNeutral80(user.name)
        Spacer4()
        TextBodyMediumNeutral80(user.email)
    }
}