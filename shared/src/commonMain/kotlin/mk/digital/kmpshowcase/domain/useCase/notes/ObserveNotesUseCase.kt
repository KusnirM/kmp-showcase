package mk.digital.kmpshowcase.domain.useCase.notes

import kotlinx.coroutines.flow.Flow
import mk.digital.kmpshowcase.domain.model.Note
import mk.digital.kmpshowcase.domain.repository.NoteRepository
import mk.digital.kmpshowcase.domain.useCase.base.FlowUseCase
import mk.digital.kmpshowcase.domain.useCase.base.None

class ObserveNotesUseCase(
    private val noteRepository: NoteRepository
) : FlowUseCase<None, List<Note>>() {
    override fun run(params: None): Flow<List<Note>> = noteRepository.observeAll()
}
