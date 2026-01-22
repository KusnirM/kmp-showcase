package mk.digital.kmpshowcase.presentation.screen.database

import mk.digital.kmpshowcase.domain.model.Note
import mk.digital.kmpshowcase.domain.useCase.base.invoke
import mk.digital.kmpshowcase.domain.useCase.notes.DeleteAllNotesUseCase
import mk.digital.kmpshowcase.domain.useCase.notes.DeleteNoteUseCase
import mk.digital.kmpshowcase.domain.useCase.notes.InsertNoteUseCase
import mk.digital.kmpshowcase.domain.useCase.notes.ObserveNotesUseCase
import mk.digital.kmpshowcase.presentation.base.BaseViewModel
import kotlin.time.Clock

class DatabaseViewModel(
    private val observeNotesUseCase: ObserveNotesUseCase,
    private val insertNoteUseCase: InsertNoteUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val deleteAllNotesUseCase: DeleteAllNotesUseCase,
) : BaseViewModel<DatabaseUiState>(DatabaseUiState()) {

    override fun loadInitialData() {
        observe(
            flow = observeNotesUseCase(),
            onEach = { notes -> newState { it.copy(notes = notes, isLoading = false) } },
            onError = { newState { it.copy(isLoading = false, error = true) } }
        )
    }

    fun onTitleChanged(title: String) {
        newState { it.copy(newNoteTitle = title) }
    }

    fun onContentChanged(content: String) {
        newState { it.copy(newNoteContent = content) }
    }

    fun addNote() {
        val currentState = state.value
        if (currentState.newNoteTitle.isBlank()) return

        val note = Note(
            title = currentState.newNoteTitle.trim(),
            content = currentState.newNoteContent.trim(),
            createdAt = Clock.System.now().toEpochMilliseconds()
        )

        execute(
            action = { insertNoteUseCase(note) },
            onSuccess = { newState { it.copy(newNoteTitle = "", newNoteContent = "") } }
        )
    }

    fun deleteNote(id: Long) {
        execute(action = { deleteNoteUseCase(id) })
    }

    fun deleteAllNotes() {
        execute(action = { deleteAllNotesUseCase() })
    }
}

data class DatabaseUiState(
    val notes: List<Note> = emptyList(),
    val isLoading: Boolean = true,
    val error: Boolean = false,
    val newNoteTitle: String = "",
    val newNoteContent: String = "",
)
