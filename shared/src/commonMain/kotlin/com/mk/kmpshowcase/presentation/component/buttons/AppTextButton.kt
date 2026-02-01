package com.mk.kmpshowcase.presentation.component.buttons

import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mk.kmpshowcase.presentation.component.text.labelLarge.TextLabelLargeError
import com.mk.kmpshowcase.presentation.component.text.labelLarge.TextLabelLargePrimary

@Composable
fun AppTextButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TextButton(
        modifier = modifier,
        onClick = onClick,
        content = {
            TextLabelLargePrimary(text)
        }
    )
}

@Composable
fun AppTextButtonError(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TextButton(
        modifier = modifier,
        onClick = onClick,
        content = {
            TextLabelLargeError(text)
        }
    )
}
