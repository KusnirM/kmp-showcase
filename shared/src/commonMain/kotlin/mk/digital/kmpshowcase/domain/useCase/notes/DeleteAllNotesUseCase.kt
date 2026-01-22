package mk.digital.kmpshowcase.domain.useCase.notes

import mk.digital.kmpshowcase.domain.repository.NoteRepository
import mk.digital.kmpshowcase.domain.useCase.base.None
import mk.digital.kmpshowcase.domain.useCase.base.UseCase

class DeleteAllNotesUseCase(
    private val noteRepository: NoteRepository
) : UseCase<None, Unit>() {
    override suspend fun run(params: None) = noteRepository.deleteAll()
}
