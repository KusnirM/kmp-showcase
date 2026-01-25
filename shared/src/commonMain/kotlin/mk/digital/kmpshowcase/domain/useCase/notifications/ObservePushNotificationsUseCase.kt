package mk.digital.kmpshowcase.domain.useCase.notifications

import kotlinx.coroutines.flow.Flow
import mk.digital.kmpshowcase.domain.model.Notification
import mk.digital.kmpshowcase.domain.repository.PushNotificationService
import mk.digital.kmpshowcase.domain.useCase.base.FlowUseCase
import mk.digital.kmpshowcase.domain.useCase.base.None

class ObservePushNotificationsUseCase(
    private val pushNotificationService: PushNotificationService
) : FlowUseCase<None, Notification>() {
    override fun run(params: None): Flow<Notification> = pushNotificationService.notifications
}
