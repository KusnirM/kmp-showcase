package mk.digital.kmpshowcase.domain.useCase.notifications

import kotlinx.coroutines.flow.StateFlow
import mk.digital.kmpshowcase.domain.repository.PushNotificationService
import mk.digital.kmpshowcase.domain.useCase.base.FlowUseCase
import mk.digital.kmpshowcase.domain.useCase.base.None

class ObservePushTokenUseCase(
    private val pushNotificationService: PushNotificationService
) : FlowUseCase<None, String?>() {
    override fun run(params: None): StateFlow<String?> = pushNotificationService.token
}
