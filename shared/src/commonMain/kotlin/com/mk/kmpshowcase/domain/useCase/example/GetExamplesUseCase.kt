package com.mk.kmpshowcase.domain.useCase.example

import com.mk.kmpshowcase.domain.model.Example
import com.mk.kmpshowcase.domain.repository.ExampleRepository
import com.mk.kmpshowcase.domain.useCase.base.None
import com.mk.kmpshowcase.domain.useCase.base.UseCase

class GetExamplesUseCase(
    private val exampleRepository: ExampleRepository
) : UseCase<None, List<Example>>() {
    override suspend fun run(params: None): List<Example> = exampleRepository.getExamples()
}
