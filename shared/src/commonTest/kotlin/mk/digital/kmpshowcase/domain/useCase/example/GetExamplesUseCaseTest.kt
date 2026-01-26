package mk.digital.kmpshowcase.domain.useCase.example

import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.mock
import dev.mokkery.verifySuspend
import kotlinx.coroutines.test.runTest
import mk.digital.kmpshowcase.domain.BaseTest
import mk.digital.kmpshowcase.domain.model.Example
import mk.digital.kmpshowcase.domain.repository.ExampleRepository
import mk.digital.kmpshowcase.domain.test
import mk.digital.kmpshowcase.domain.useCase.base.invoke
import kotlin.test.Test
import kotlin.test.assertEquals

class GetExamplesUseCaseTest : BaseTest<GetExamplesUseCase>() {
    override lateinit var classUnderTest: GetExamplesUseCase

    private val exampleRepository: ExampleRepository = mock()

    override fun beforeEach() {
        classUnderTest = GetExamplesUseCase(exampleRepository)
    }

    @Test
    fun testSuccess() = runTest {
        val expectedExamples = listOf(
            Example(id = 1, name = "Example 1"),
            Example(id = 2, name = "Example 2"),
        )

        test(
            given = {
                everySuspend { exampleRepository.getExamples() } returns expectedExamples
            },
            whenAction = {
                classUnderTest()
            },
            then = { result ->
                assertEquals(expectedExamples, result)
                verifySuspend { exampleRepository.getExamples() }
            }
        )
    }
}
