package mk.digital.kmpsample.presentation.component

import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import mk.digital.kmpsample.presentation.foundation.appColors

@Composable
fun AppCheckbox(
    checked: Boolean = false,
    onCheckedChange: ((Boolean) -> Unit)? = null,
) {
    Checkbox(
        checked = checked,
        onCheckedChange = onCheckedChange,
        colors = CheckboxDefaults.colors(
            checkedColor = MaterialTheme.appColors.primary,
        )
    )
}
