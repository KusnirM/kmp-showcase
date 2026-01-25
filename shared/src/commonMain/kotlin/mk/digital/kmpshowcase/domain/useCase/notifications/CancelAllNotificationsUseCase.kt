package mk.digital.kmpshowcase.domain.useCase.notifications

import mk.digital.kmpshowcase.domain.repository.LocalNotificationService
import mk.digital.kmpshowcase.domain.useCase.base.None
import mk.digital.kmpshowcase.domain.useCase.base.UseCase

class CancelAllNotificationsUseCase(
    private val localNotificationService: LocalNotificationService
) : UseCase<None, Unit>() {
    override suspend fun run(params: None) = localNotificationService.cancelAllNotifications()
}
