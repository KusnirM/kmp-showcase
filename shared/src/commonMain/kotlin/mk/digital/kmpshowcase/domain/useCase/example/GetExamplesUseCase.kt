package mk.digital.kmpshowcase.domain.useCase.example

import mk.digital.kmpshowcase.domain.model.Example
import mk.digital.kmpshowcase.domain.repository.ExampleRepository
import mk.digital.kmpshowcase.domain.useCase.base.None
import mk.digital.kmpshowcase.domain.useCase.base.UseCase

class GetExamplesUseCase(
    private val exampleRepository: ExampleRepository
) : UseCase<None, List<Example>>() {
    override suspend fun run(params: None): List<Example> = exampleRepository.getExamples()
}
