package mk.digital.kmpshowcase.domain.useCase.notifications

import mk.digital.kmpshowcase.domain.model.Notification
import mk.digital.kmpshowcase.domain.repository.LocalNotificationService
import mk.digital.kmpshowcase.domain.useCase.base.UseCase

class ShowLocalNotificationUseCase(
    private val localNotificationService: LocalNotificationService
) : UseCase<Notification, Unit>() {
    override suspend fun run(params: Notification) = localNotificationService.showNotification(params)
}
