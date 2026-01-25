package mk.digital.kmpshowcase.domain.useCase.notifications

import mk.digital.kmpshowcase.domain.repository.PushNotificationService
import mk.digital.kmpshowcase.domain.repository.PushPermissionStatus
import mk.digital.kmpshowcase.domain.useCase.base.None
import mk.digital.kmpshowcase.domain.useCase.base.UseCase

class GetPushPermissionStatusUseCase(
    private val pushNotificationService: PushNotificationService
) : UseCase<None, PushPermissionStatus>() {
    override suspend fun run(params: None): PushPermissionStatus =
        pushNotificationService.getPermissionStatus()
}
