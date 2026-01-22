package mk.digital.kmpshowcase.presentation.component.permission

import androidx.compose.runtime.Composable

@Composable
expect fun RecheckOnResume(onResume: () -> Unit)

expect val galleryRequiresPermission: Boolean
