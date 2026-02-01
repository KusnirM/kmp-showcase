package com.mk.kmpshowcase.domain.repository

import com.mk.kmpshowcase.domain.model.Example

interface ExampleRepository {
    suspend fun getExample(id: Int): Example
    suspend fun getExamples(): List<Example>
}
