package mk.digital.kmpshowcase.presentation.screen.database

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import mk.digital.kmpshowcase.domain.model.Note
import mk.digital.kmpshowcase.presentation.component.AppTextField
import mk.digital.kmpshowcase.presentation.component.buttons.ContainedButton
import mk.digital.kmpshowcase.presentation.component.buttons.OutlinedButton
import mk.digital.kmpshowcase.presentation.component.cards.AppElevatedCard
import mk.digital.kmpshowcase.presentation.component.spacers.ColumnSpacer.Spacer2
import mk.digital.kmpshowcase.presentation.component.text.bodyMedium.TextBodyMediumNeutral80
import mk.digital.kmpshowcase.presentation.component.text.bodySmall.TextBodySmallNeutral80
import mk.digital.kmpshowcase.presentation.component.text.headlineMedium.TextHeadlineMediumPrimary
import mk.digital.kmpshowcase.presentation.component.text.titleLarge.TextTitleLargeNeutral80
import mk.digital.kmpshowcase.presentation.foundation.floatingNavBarSpace
import mk.digital.kmpshowcase.presentation.foundation.space4
import mk.digital.kmpshowcase.shared.generated.resources.Res
import mk.digital.kmpshowcase.shared.generated.resources.database_add_note
import mk.digital.kmpshowcase.shared.generated.resources.database_clear_all
import mk.digital.kmpshowcase.shared.generated.resources.database_content_hint
import mk.digital.kmpshowcase.shared.generated.resources.database_content_label
import mk.digital.kmpshowcase.shared.generated.resources.database_empty
import mk.digital.kmpshowcase.shared.generated.resources.database_subtitle
import mk.digital.kmpshowcase.shared.generated.resources.database_title
import mk.digital.kmpshowcase.shared.generated.resources.database_title_hint
import mk.digital.kmpshowcase.shared.generated.resources.database_title_label
import org.jetbrains.compose.resources.stringResource
import kotlin.time.Instant

@Composable
fun DatabaseScreen(viewModel: DatabaseViewModel) {
    val state by viewModel.state.collectAsStateWithLifecycle()

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
                TextHeadlineMediumPrimary(stringResource(Res.string.database_title))
                TextBodyMediumNeutral80(stringResource(Res.string.database_subtitle))
            }
        }

        item {
            AddNoteCard(
                title = state.newNoteTitle,
                content = state.newNoteContent,
                onTitleChanged = viewModel::onTitleChanged,
                onContentChanged = viewModel::onContentChanged,
                onAddClick = viewModel::addNote
            )
        }

        if (state.notes.isEmpty() && !state.isLoading) {
            item {
                TextBodyMediumNeutral80(stringResource(Res.string.database_empty))
            }
        }

        items(items = state.notes, key = { it.id }) { note ->
            NoteCard(
                note = note,
                onDeleteClick = { viewModel.deleteNote(note.id) }
            )
        }

        if (state.notes.isNotEmpty()) {
            item {
                OutlinedButton(
                    text = stringResource(Res.string.database_clear_all),
                    onClick = viewModel::deleteAllNotes
                )
            }
        }
    }
}

@Composable
private fun AddNoteCard(
    title: String,
    content: String,
    onTitleChanged: (String) -> Unit,
    onContentChanged: (String) -> Unit,
    onAddClick: () -> Unit,
) {
    AppElevatedCard(modifier = Modifier.fillMaxWidth().padding(space4)) {
        AppTextField(
            value = title,
            onValueChange = onTitleChanged,
            label = stringResource(Res.string.database_title_label),
            placeholder = stringResource(Res.string.database_title_hint),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer2()
        AppTextField(
            value = content,
            onValueChange = onContentChanged,
            label = stringResource(Res.string.database_content_label),
            placeholder = stringResource(Res.string.database_content_hint),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer2()
        ContainedButton(
            text = stringResource(Res.string.database_add_note),
            onClick = onAddClick,
            enabled = title.isNotBlank()
        )
    }
}

@Composable
private fun NoteCard(
    note: Note,
    onDeleteClick: () -> Unit,
) {
    AppElevatedCard(modifier = Modifier.fillMaxWidth().padding(space4)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Column(modifier = Modifier.weight(1f)) {
                TextTitleLargeNeutral80(note.title)
                if (note.content.isNotBlank()) {
                    Spacer2()
                    TextBodyMediumNeutral80(note.content)
                }
                Spacer2()
                TextBodySmallNeutral80(formatTimestamp(note.createdAt))
            }
            IconButton(onClick = onDeleteClick) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "Delete",
                    tint = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}

private fun formatTimestamp(timestamp: Long): String {
    val instant = Instant.fromEpochMilliseconds(timestamp)
    val localDateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())
    return "${localDateTime.date} ${localDateTime.hour.toString().padStart(2, '0')}:${
        localDateTime.minute.toString().padStart(2, '0')
    }"
}
