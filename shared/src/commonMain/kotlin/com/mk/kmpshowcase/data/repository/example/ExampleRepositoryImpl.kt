package com.mk.kmpshowcase.data.repository.example

import com.mk.kmpshowcase.data.base.transformAll
import com.mk.kmpshowcase.domain.model.Example
import com.mk.kmpshowcase.domain.repository.ExampleRepository

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
