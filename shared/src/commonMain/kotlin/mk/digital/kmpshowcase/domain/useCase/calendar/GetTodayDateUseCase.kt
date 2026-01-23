package mk.digital.kmpshowcase.domain.useCase.calendar

import kotlinx.datetime.LocalDate
import mk.digital.kmpshowcase.domain.repository.DateRepository
import mk.digital.kmpshowcase.domain.useCase.base.None
import mk.digital.kmpshowcase.domain.useCase.base.UseCase

class GetTodayDateUseCase(
    private val dateRepository: DateRepository,
) : UseCase<None, LocalDate>() {
    override suspend fun run(params: None): LocalDate = dateRepository.today()
}
