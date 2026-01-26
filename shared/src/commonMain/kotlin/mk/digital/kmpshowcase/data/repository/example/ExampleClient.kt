package mk.digital.kmpshowcase.data.repository.example

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import mk.digital.kmpshowcase.data.dto.ExampleDTO
import mk.digital.kmpshowcase.data.network.handleApiCall

interface ExampleClient {
    suspend fun fetchExample(id: Int): ExampleDTO
    suspend fun fetchExamples(): List<ExampleDTO>
}

class ExampleClientImpl(
    private val client: HttpClient
) : ExampleClient {

    override suspend fun fetchExample(id: Int): ExampleDTO = handleApiCall {
        client.get("examples/$id").body()
    }

    override suspend fun fetchExamples(): List<ExampleDTO> = handleApiCall {
        client.get("examples").body()
    }
}
