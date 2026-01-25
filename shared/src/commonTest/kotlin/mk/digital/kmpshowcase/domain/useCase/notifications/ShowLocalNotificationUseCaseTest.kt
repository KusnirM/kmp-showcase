package mk.digital.kmpshowcase.domain.useCase.notifications

import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.mock
import dev.mokkery.verifySuspend
import kotlinx.coroutines.test.runTest
import mk.digital.kmpshowcase.domain.BaseTest
import mk.digital.kmpshowcase.domain.model.Notification
import mk.digital.kmpshowcase.domain.model.NotificationChannel
import mk.digital.kmpshowcase.domain.repository.LocalNotificationService
import mk.digital.kmpshowcase.domain.test
import kotlin.test.Test

class ShowLocalNotificationUseCaseTest : BaseTest<ShowLocalNotificationUseCase>() {
    override lateinit var classUnderTest: ShowLocalNotificationUseCase

    private val localNotificationService: LocalNotificationService = mock()

    override fun beforeEach() {
        classUnderTest = ShowLocalNotificationUseCase(localNotificationService)
    }

    @Test
    fun `invoke calls service showNotification`() = runTest {
        val notification = Notification(
            id = "test-id",
            title = "Test Title",
            message = "Test Message",
            channel = NotificationChannel.GENERAL
        )

        test(
            given = {
                everySuspend { localNotificationService.showNotification(notification) } returns Unit
            },
            whenAction = {
                classUnderTest(notification)
            },
            then = {
                verifySuspend { localNotificationService.showNotification(notification) }
            }
        )
    }
}
