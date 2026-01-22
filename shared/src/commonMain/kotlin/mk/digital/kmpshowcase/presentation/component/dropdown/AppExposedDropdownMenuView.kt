package mk.digital.kmpshowcase.presentation.component.dropdown

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import mk.digital.kmpshowcase.presentation.component.AppTextField
import mk.digital.kmpshowcase.presentation.component.image.AppIconNeutral80
import mk.digital.kmpshowcase.presentation.foundation.AppIcons
import mk.digital.kmpshowcase.presentation.foundation.appColorScheme
import mk.digital.kmpshowcase.presentation.foundation.space4

@Composable
fun AppExposedDropdownMenuView(
    items: List<Pair<String, Painter?>>,
    selectedIndex: Int,
    onItemClick: (index: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    var expanded by remember { mutableStateOf(false) }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }
    val focusManager = LocalFocusManager.current

    val selectedItem = items.getOrNull(selectedIndex) ?: ("" to null)

    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        val textFieldModifier = Modifier
            .fillMaxWidth()
            .menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable)
            .onGloballyPositioned { textFieldSize = it.size.toSize() }

        AppTextField(
            value = selectedItem.first,
            onValueChange = { },
            leadingIcon = selectedItem.second?.let { icon ->
                { Image(painter = icon, modifier = Modifier.size(24.dp), contentDescription = null) }
            },
            modifier = textFieldModifier,
            readOnly = true,
            showClearButton = false,
            trailingIcon = {
                AppIconNeutral80(
                    imageVector = if (expanded) AppIcons.ChevronUp else AppIcons.ChevronDown
                )
            }
        )

        ExposedDropdownMenu(
            modifier = Modifier.width(with(LocalDensity.current) { textFieldSize.width.toDp() }),
            expanded = expanded,
            containerColor = MaterialTheme.colorScheme.background,
            shape = RoundedCornerShape(space4),
            onDismissRequest = {
                expanded = false
                focusManager.clearFocus()
            }
        ) {
            items.forEachIndexed { index, item ->
                DropdownMenuItem(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(
                            if (index == selectedIndex) MaterialTheme.appColorScheme.neutral20
                            else MaterialTheme.colorScheme.background
                        ),
                    leadingIcon = item.second?.let { icon ->
                        { Image(painter = icon, modifier = Modifier.size(24.dp), contentDescription = null) }
                    },
                    text = {
                        Text(
                            text = item.first,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.appColorScheme.neutral80,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    onClick = {
                        expanded = false
                        onItemClick(index)
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                )
            }
        }
    }
}
