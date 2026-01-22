package mk.digital.kmpshowcase.domain.useCase.notes

import mk.digital.kmpshowcase.domain.repository.NoteRepository
import mk.digital.kmpshowcase.domain.useCase.base.UseCase

class DeleteNoteUseCase(
    private val noteRepository: NoteRepository
) : UseCase<Long, Unit>() {
    override suspend fun run(params: Long) = noteRepository.delete(params)
}
