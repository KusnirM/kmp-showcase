package mk.digital.kmpshowcase.domain.useCase.notes

import mk.digital.kmpshowcase.domain.model.Note
import mk.digital.kmpshowcase.domain.repository.NoteRepository
import mk.digital.kmpshowcase.domain.useCase.base.UseCase

class InsertNoteUseCase(
    private val noteRepository: NoteRepository
) : UseCase<Note, Unit>() {
    override suspend fun run(params: Note) = noteRepository.insert(params)
}
