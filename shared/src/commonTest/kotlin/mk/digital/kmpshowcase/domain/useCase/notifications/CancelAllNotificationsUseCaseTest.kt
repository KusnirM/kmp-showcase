package mk.digital.kmpshowcase.domain.useCase.notifications

import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.mock
import dev.mokkery.verifySuspend
import kotlinx.coroutines.test.runTest
import mk.digital.kmpshowcase.domain.BaseTest
import mk.digital.kmpshowcase.domain.repository.LocalNotificationService
import mk.digital.kmpshowcase.domain.test
import mk.digital.kmpshowcase.domain.useCase.base.invoke
import kotlin.test.Test

class CancelAllNotificationsUseCaseTest : BaseTest<CancelAllNotificationsUseCase>() {
    override lateinit var classUnderTest: CancelAllNotificationsUseCase

    private val localNotificationService: LocalNotificationService = mock()

    override fun beforeEach() {
        classUnderTest = CancelAllNotificationsUseCase(localNotificationService)
    }

    @Test
    fun `invoke calls service cancelAllNotifications`() = runTest {
        test(
            given = {
                everySuspend { localNotificationService.cancelAllNotifications() } returns Unit
            },
            whenAction = {
                classUnderTest()
            },
            then = {
                verifySuspend { localNotificationService.cancelAllNotifications() }
            }
        )
    }
}
