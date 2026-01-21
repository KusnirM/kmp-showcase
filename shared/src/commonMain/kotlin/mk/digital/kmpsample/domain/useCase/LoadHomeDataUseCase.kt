package mk.digital.kmpsample.domain.useCase

import mk.digital.kmpsample.domain.model.User
import mk.digital.kmpsample.domain.repository.UserRepository
import mk.digital.kmpsample.domain.useCase.base.None
import mk.digital.kmpsample.domain.useCase.base.UseCase

class LoadHomeDataUseCase(
    private val userRepository: UserRepository
) : UseCase<None, List<User>>() {
    override suspend fun run(params: None): List<User> = userRepository.getUsers()
}
