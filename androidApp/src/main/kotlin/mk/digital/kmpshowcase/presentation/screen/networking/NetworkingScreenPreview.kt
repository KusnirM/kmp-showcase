package mk.digital.kmpshowcase.presentation.screen.networking

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import mk.digital.kmpshowcase.domain.model.Address
import mk.digital.kmpshowcase.domain.model.User
import mk.digital.kmpshowcase.presentation.foundation.AppTheme

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun NetworkingScreenPreview(
    @PreviewParameter(NetworkingScreenPreviewParams::class) state: NetworkingUiState
) {
    AppTheme {
        NetworkingScreen(state = state)
    }
}

internal class NetworkingScreenPreviewParams : PreviewParameterProvider<NetworkingUiState> {
    override val values = sequenceOf(
        NetworkingUiState(isLoading = true),
        NetworkingUiState(error = "401"),
        NetworkingUiState(
            users = listOf(
                User(
                    address = Address(
                        city = "city",
                        street = "street",
                        suite = "suite",
                        zipcode = "zipcode"
                    ),
                    email = "mir.kusnir@gmail.com",
                    id = 1,
                    name = "Miroslav Coder"
                )
            )
        )
    )
}
