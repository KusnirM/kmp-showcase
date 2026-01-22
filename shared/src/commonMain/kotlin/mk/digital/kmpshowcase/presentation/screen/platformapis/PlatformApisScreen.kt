package mk.digital.kmpshowcase.presentation.screen.platformapis

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay
import mk.digital.kmpshowcase.domain.model.Location
import mk.digital.kmpshowcase.LocalSnackbarHostState
import mk.digital.kmpshowcase.presentation.component.buttons.OutlinedButton
import mk.digital.kmpshowcase.presentation.component.permission.rememberLocationPermissionState
import mk.digital.kmpshowcase.presentation.component.cards.AppElevatedCard
import mk.digital.kmpshowcase.presentation.component.spacers.ColumnSpacer.Spacer2
import mk.digital.kmpshowcase.presentation.component.text.bodyMedium.TextBodyMediumNeutral80
import mk.digital.kmpshowcase.presentation.component.text.headlineMedium.TextHeadlineMediumPrimary
import mk.digital.kmpshowcase.presentation.component.text.bodyLarge.TextBodyLargeNeutral80
import mk.digital.kmpshowcase.presentation.foundation.floatingNavBarSpace
import mk.digital.kmpshowcase.presentation.foundation.space4
import mk.digital.kmpshowcase.shared.generated.resources.Res
import mk.digital.kmpshowcase.shared.generated.resources.platform_apis_copy_action
import mk.digital.kmpshowcase.shared.generated.resources.platform_apis_copy_hint
import mk.digital.kmpshowcase.shared.generated.resources.platform_apis_copy_title
import mk.digital.kmpshowcase.shared.generated.resources.platform_apis_copied_message
import mk.digital.kmpshowcase.shared.generated.resources.platform_apis_demo_copy_text
import mk.digital.kmpshowcase.shared.generated.resources.platform_apis_demo_email_body
import mk.digital.kmpshowcase.shared.generated.resources.platform_apis_demo_email_subject
import mk.digital.kmpshowcase.shared.generated.resources.platform_apis_demo_share_text
import mk.digital.kmpshowcase.shared.generated.resources.platform_apis_dial_action
import mk.digital.kmpshowcase.shared.generated.resources.platform_apis_email_action
import mk.digital.kmpshowcase.shared.generated.resources.platform_apis_email_hint
import mk.digital.kmpshowcase.shared.generated.resources.platform_apis_email_title
import mk.digital.kmpshowcase.shared.generated.resources.platform_apis_dial_hint
import mk.digital.kmpshowcase.shared.generated.resources.platform_apis_dial_title
import mk.digital.kmpshowcase.shared.generated.resources.platform_apis_link_action
import mk.digital.kmpshowcase.shared.generated.resources.platform_apis_link_hint
import mk.digital.kmpshowcase.shared.generated.resources.platform_apis_link_title
import mk.digital.kmpshowcase.shared.generated.resources.platform_apis_location_action
import mk.digital.kmpshowcase.shared.generated.resources.platform_apis_location_error
import mk.digital.kmpshowcase.shared.generated.resources.platform_apis_location_hint
import mk.digital.kmpshowcase.shared.generated.resources.platform_apis_location_loading
import mk.digital.kmpshowcase.shared.generated.resources.platform_apis_location_result
import mk.digital.kmpshowcase.shared.generated.resources.platform_apis_location_title
import mk.digital.kmpshowcase.shared.generated.resources.platform_apis_location_updates_error
import mk.digital.kmpshowcase.shared.generated.resources.platform_apis_location_updates_hint
import mk.digital.kmpshowcase.shared.generated.resources.platform_apis_location_updates_start
import mk.digital.kmpshowcase.shared.generated.resources.platform_apis_location_updates_stop
import mk.digital.kmpshowcase.shared.generated.resources.platform_apis_location_updates_title
import mk.digital.kmpshowcase.shared.generated.resources.platform_apis_share_action
import mk.digital.kmpshowcase.shared.generated.resources.platform_apis_share_hint
import mk.digital.kmpshowcase.shared.generated.resources.platform_apis_share_title
import mk.digital.kmpshowcase.shared.generated.resources.platform_apis_subtitle
import mk.digital.kmpshowcase.shared.generated.resources.platform_apis_title
import mk.digital.kmpshowcase.util.StringFormatter
import org.jetbrains.compose.resources.stringResource

private const val DEMO_PHONE_NUMBER = "+1234567890"
private const val DEMO_URL = "https://github.com/KusnirM/kmp-showcase"
private const val DEMO_EMAIL = "mir.kusnir@gmail.com"

private enum class PendingLocationAction { NONE, GET_LOCATION, START_UPDATES }

@Composable
private fun rememberLocationActionHandler(
    onGetLocation: () -> Unit,
    onStartUpdates: () -> Unit
): (PendingLocationAction) -> Unit {
    val locationPermission = rememberLocationPermissionState()
    var pendingAction by remember { mutableStateOf(PendingLocationAction.NONE) }

    LaunchedEffect(locationPermission.isGranted, pendingAction) {
        if (locationPermission.isGranted && pendingAction != PendingLocationAction.NONE) {
            when (pendingAction) {
                PendingLocationAction.GET_LOCATION -> onGetLocation()
                PendingLocationAction.START_UPDATES -> onStartUpdates()
                PendingLocationAction.NONE -> Unit
            }
            pendingAction = PendingLocationAction.NONE
        }
    }

    return { action ->
        if (locationPermission.isGranted) {
            when (action) {
                PendingLocationAction.GET_LOCATION -> onGetLocation()
                PendingLocationAction.START_UPDATES -> onStartUpdates()
                PendingLocationAction.NONE -> Unit
            }
        } else {
            pendingAction = action
            locationPermission.requestPermission()
        }
    }
}

@Composable
fun PlatformApisScreen(viewModel: PlatformApisViewModel) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val snackbarHostState = LocalSnackbarHostState.current
    val copiedMessage = stringResource(Res.string.platform_apis_copied_message)
    val demoShareText = stringResource(Res.string.platform_apis_demo_share_text)
    val demoCopyText = stringResource(Res.string.platform_apis_demo_copy_text)
    val demoEmailSubject = stringResource(Res.string.platform_apis_demo_email_subject)
    val demoEmailBody = stringResource(Res.string.platform_apis_demo_email_body)

    val handleLocationAction = rememberLocationActionHandler(
        onGetLocation = { viewModel.getLocation() },
        onStartUpdates = { viewModel.startLocationUpdates() }
    )

    LaunchedEffect(state.copiedToClipboard) {
        if (state.copiedToClipboard) {
            snackbarHostState.showSnackbar(copiedMessage)
            delay(100)
            viewModel.resetCopyState()
        }
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            start = space4,
            end = space4,
            top = space4,
            bottom = floatingNavBarSpace
        ),
        verticalArrangement = Arrangement.spacedBy(space4)
    ) {
        item {
            Column {
                TextHeadlineMediumPrimary(stringResource(Res.string.platform_apis_title))
                TextBodyMediumNeutral80(stringResource(Res.string.platform_apis_subtitle))
            }
        }

        item {
            ApiCard(
                title = stringResource(Res.string.platform_apis_share_title),
                hint = stringResource(Res.string.platform_apis_share_hint),
                actionText = stringResource(Res.string.platform_apis_share_action),
                onClick = { viewModel.share(demoShareText) }
            )
        }

        item {
            ApiCard(
                title = stringResource(Res.string.platform_apis_dial_title),
                hint = stringResource(Res.string.platform_apis_dial_hint),
                actionText = stringResource(Res.string.platform_apis_dial_action),
                onClick = { viewModel.dial(DEMO_PHONE_NUMBER) }
            )
        }

        item {
            ApiCard(
                title = stringResource(Res.string.platform_apis_link_title),
                hint = stringResource(Res.string.platform_apis_link_hint),
                actionText = stringResource(Res.string.platform_apis_link_action),
                onClick = { viewModel.openLink(DEMO_URL) }
            )
        }

        item {
            ApiCard(
                title = stringResource(Res.string.platform_apis_email_title),
                hint = stringResource(Res.string.platform_apis_email_hint),
                actionText = stringResource(Res.string.platform_apis_email_action),
                onClick = { viewModel.sendEmail(DEMO_EMAIL, demoEmailSubject, demoEmailBody) }
            )
        }

        item {
            ApiCard(
                title = stringResource(Res.string.platform_apis_copy_title),
                hint = stringResource(Res.string.platform_apis_copy_hint),
                actionText = stringResource(Res.string.platform_apis_copy_action),
                onClick = { viewModel.copyToClipboard(demoCopyText) }
            )
        }

        item {
            LocationCard(
                title = stringResource(Res.string.platform_apis_location_title),
                hint = stringResource(Res.string.platform_apis_location_hint),
                actionText = stringResource(Res.string.platform_apis_location_action),
                loadingText = stringResource(Res.string.platform_apis_location_loading),
                errorText = stringResource(Res.string.platform_apis_location_error),
                location = state.location,
                isLoading = state.locationLoading,
                isError = state.locationError,
                onClick = { handleLocationAction(PendingLocationAction.GET_LOCATION) }
            )
        }

        item {
            LocationUpdatesCard(
                title = stringResource(Res.string.platform_apis_location_updates_title),
                hint = stringResource(Res.string.platform_apis_location_updates_hint),
                startText = stringResource(Res.string.platform_apis_location_updates_start),
                stopText = stringResource(Res.string.platform_apis_location_updates_stop),
                errorText = stringResource(Res.string.platform_apis_location_updates_error),
                location = state.trackedLocation,
                isTracking = state.isTrackingLocation,
                isError = state.locationUpdatesError,
                onStart = { handleLocationAction(PendingLocationAction.START_UPDATES) },
                onStop = { viewModel.stopLocationUpdates() }
            )
        }
    }
}

@Composable
private fun ApiCard(
    title: String,
    hint: String,
    actionText: String,
    onClick: () -> Unit
) {
    AppElevatedCard(modifier = Modifier.fillMaxWidth().padding(space4)) {
        TextBodyLargeNeutral80(title)
        Spacer2()
        TextBodyMediumNeutral80(hint)
        Spacer2()
        OutlinedButton(text = actionText, onClick = onClick)
    }
}

@Suppress("LongParameterList")
@Composable
private fun LocationCard(
    title: String,
    hint: String,
    actionText: String,
    loadingText: String,
    errorText: String,
    location: Location?,
    isLoading: Boolean,
    isError: Boolean,
    onClick: () -> Unit
) {
    AppElevatedCard(modifier = Modifier.fillMaxWidth().padding(space4)) {
        TextBodyLargeNeutral80(title)
        Spacer2()
        TextBodyMediumNeutral80(hint)
        Spacer2()
        when {
            isLoading -> TextBodyMediumNeutral80(loadingText)
            isError -> TextBodyMediumNeutral80(errorText)
            location != null -> TextBodyMediumNeutral80(
                formatLocationText(location.lat, location.lon)
            )
        }
        Spacer2()
        OutlinedButton(text = actionText, onClick = onClick)
    }
}

@Suppress("LongParameterList")
@Composable
private fun LocationUpdatesCard(
    title: String,
    hint: String,
    startText: String,
    stopText: String,
    errorText: String,
    location: Location?,
    isTracking: Boolean,
    isError: Boolean,
    onStart: () -> Unit,
    onStop: () -> Unit
) {
    AppElevatedCard(modifier = Modifier.fillMaxWidth().padding(space4)) {
        TextBodyLargeNeutral80(title)
        Spacer2()
        TextBodyMediumNeutral80(hint)
        Spacer2()
        when {
            isError -> TextBodyMediumNeutral80(errorText)
            location != null -> TextBodyMediumNeutral80(
                formatLocationText(location.lat, location.lon)
            )
        }
        Spacer2()
        OutlinedButton(
            text = if (isTracking) stopText else startText,
            onClick = if (isTracking) onStop else onStart
        )
    }
}

private const val COORDINATE_DECIMAL_PLACES = 6

@Composable
private fun formatLocationText(lat: Double, lon: Double): String {
    return stringResource(
        Res.string.platform_apis_location_result,
        StringFormatter.formatDouble(lat, COORDINATE_DECIMAL_PLACES),
        StringFormatter.formatDouble(lon, COORDINATE_DECIMAL_PLACES)
    )
}
