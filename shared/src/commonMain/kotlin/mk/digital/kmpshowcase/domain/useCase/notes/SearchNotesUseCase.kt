package mk.digital.kmpshowcase.domain.useCase.notes

import kotlinx.coroutines.flow.Flow
import mk.digital.kmpshowcase.domain.model.Note
import mk.digital.kmpshowcase.domain.model.NoteSortOption
import mk.digital.kmpshowcase.domain.repository.NoteRepository
import mk.digital.kmpshowcase.domain.useCase.base.FlowUseCase

class SearchNotesUseCase(
    private val repository: NoteRepository
) : FlowUseCase<SearchNotesUseCase.Params, List<Note>>() {

    override fun run(params: Params): Flow<List<Note>> {
        return if (params.query.isBlank()) {
            repository.observeAll(params.sortOption)
        } else {
            repository.search(params.query, params.sortOption)
        }
    }

    data class Params(
        val query: String = "",
        val sortOption: NoteSortOption = NoteSortOption.DATE_DESC,
    )
}
