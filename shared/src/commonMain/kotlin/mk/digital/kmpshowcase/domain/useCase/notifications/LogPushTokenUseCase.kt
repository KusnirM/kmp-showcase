package mk.digital.kmpshowcase.domain.useCase.notifications

import mk.digital.kmpshowcase.domain.repository.PushNotificationService
import mk.digital.kmpshowcase.domain.useCase.base.None
import mk.digital.kmpshowcase.domain.useCase.base.UseCase

class LogPushTokenUseCase(
    private val pushNotificationService: PushNotificationService
) : UseCase<None, Unit>() {
    override suspend fun run(params: None) = pushNotificationService.logToken()
}
