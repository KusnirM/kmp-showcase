package mk.digital.kmpshowcase.domain.useCase.notifications

import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.mock
import dev.mokkery.verifySuspend
import kotlinx.coroutines.test.runTest
import mk.digital.kmpshowcase.domain.BaseTest
import mk.digital.kmpshowcase.domain.repository.PushNotificationService
import mk.digital.kmpshowcase.domain.test
import mk.digital.kmpshowcase.domain.useCase.base.invoke
import kotlin.test.Test

class LogPushTokenUseCaseTest : BaseTest<LogPushTokenUseCase>() {
    override lateinit var classUnderTest: LogPushTokenUseCase

    private val pushNotificationService: PushNotificationService = mock()

    override fun beforeEach() {
        classUnderTest = LogPushTokenUseCase(pushNotificationService)
    }

    @Test
    fun `invoke calls service logToken`() = runTest {
        test(
            given = {
                everySuspend { pushNotificationService.logToken() } returns Unit
            },
            whenAction = {
                classUnderTest()
            },
            then = {
                verifySuspend { pushNotificationService.logToken() }
            }
        )
    }
}
