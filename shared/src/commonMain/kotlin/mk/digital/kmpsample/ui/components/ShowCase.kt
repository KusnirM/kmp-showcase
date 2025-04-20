package mk.digital.kmpsample.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.AddToHomeScreen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import mk.digital.kmpsample.ui.components.buttons.AppTextButton
import mk.digital.kmpsample.ui.components.buttons.ContainedButton
import mk.digital.kmpsample.ui.components.buttons.OutlinedButton
import mk.digital.kmpsample.ui.components.cards.AppCard
import mk.digital.kmpsample.ui.components.dividers.AppDividerPrimary
import mk.digital.kmpsample.ui.components.image.AppImage
import mk.digital.kmpsample.ui.components.spacers.ColumnSpacer.Spacer4
import mk.digital.kmpsample.ui.components.text.button.TextButtonPrimary
import mk.digital.kmpsample.ui.components.text.h4.TextH4Primary
import mk.digital.kmpsample.ui.foundation.AppTheme
import mk.digital.kmpsample.ui.foundation.space4

@Composable
fun ShowCase() {
    AppTheme {
        Column {
            TopAppBar(title = "TopAppBar")
            Spacer4()
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(space4)
            ) {
                Spacer4()
                AppDividerPrimary()
                Spacer4()
                AppTextButton("Text Button", onClick = {})
                Spacer4()
                ContainedButton(text = "Contained Button", onClick = {})
                Spacer4()
                OutlinedButton(text = "Outlined Button", onClick = {})
                Spacer4()
                val showDialog = remember { mutableStateOf(false) }
                AppCard(
                    modifier = Modifier.clickable {
                        showDialog.value = !showDialog.value
                    },
                    contentPadding = PaddingValues(space4),
                ) {
                    TextH4Primary("Text H4 Primary")
                    Spacer4()
                    TextButtonPrimary("App card - Text Button Primary")
                }
                Spacer4()
                AppImage(
                    imageVector = Icons.AutoMirrored.Filled.AddToHomeScreen,
                    colorFilter = ColorFilter.tint(MaterialTheme.colors.primary)
                )
                Spacer4()
                CircularProgress()
                Spacer4()
                val checkedCheckBox = remember { mutableStateOf(false) }
                AppCheckbox(
                    checked = checkedCheckBox.value,
                    onCheckedChange = { checkedCheckBox.value = !checkedCheckBox.value })
                Spacer4()

                if (showDialog.value) {
                    AppConfirmDialog(
                        onDismissRequest = {
                            showDialog.value = false
                        },
                        title = "Title",
                        message = "body"
                    )
                }
            }
        }
    }
}