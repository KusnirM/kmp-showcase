package mk.digital.kmpshowcase.data.repository.example

import mk.digital.kmpshowcase.data.base.transformAll
import mk.digital.kmpshowcase.domain.model.Example
import mk.digital.kmpshowcase.domain.repository.ExampleRepository

class ExampleRepositoryImpl(
    private val client: ExampleClient
) : ExampleRepository {

    override suspend fun getExample(id: Int): Example {
        return client.fetchExample(id).transform()
    }

    override suspend fun getExamples(): List<Example> {
        return client.fetchExamples().transformAll()
    }
}
