package mk.digital.kmpshowcase.domain.repository

import mk.digital.kmpshowcase.domain.model.Example

interface ExampleRepository {
    suspend fun getExample(id: Int): Example
    suspend fun getExamples(): List<Example>
}
