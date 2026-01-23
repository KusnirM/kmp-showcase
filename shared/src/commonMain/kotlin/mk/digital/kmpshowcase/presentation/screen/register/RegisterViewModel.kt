package mk.digital.kmpshowcase.presentation.screen.register

import mk.digital.kmpshowcase.domain.model.RegisteredUser
import mk.digital.kmpshowcase.domain.useCase.auth.CheckEmailExistsUseCase
import mk.digital.kmpshowcase.domain.useCase.auth.RegisterUserUseCase
import mk.digital.kmpshowcase.presentation.base.BaseViewModel
import mk.digital.kmpshowcase.presentation.base.NavEvent

class RegisterViewModel(
    private val checkEmailExistsUseCase: CheckEmailExistsUseCase,
    private val registerUserUseCase: RegisterUserUseCase,
) : BaseViewModel<RegisterUiState>(RegisterUiState()) {

    fun onNameChange(name: String) {
        newState {
            it.copy(
                name = name,
                nameError = null
            )
        }
    }

    fun onEmailChange(email: String) {
        newState {
            it.copy(
                email = email,
                emailError = null
            )
        }
    }

    fun onPasswordChange(password: String) {
        newState {
            it.copy(
                password = password,
                passwordError = null
            )
        }
    }

    fun onConfirmPasswordChange(confirmPassword: String) {
        newState {
            it.copy(
                confirmPassword = confirmPassword,
                confirmPasswordError = null
            )
        }
    }

    fun register() {
        requireState { state ->
            val nameError = validateName(state.name)
            val emailError = validateEmail(state.email)
            val passwordError = validatePassword(state.password)
            val confirmPasswordError = validateConfirmPassword(state.password, state.confirmPassword)

            if (nameError != null || emailError != null || passwordError != null || confirmPasswordError != null) {
                newState {
                    it.copy(
                        nameError = nameError,
                        emailError = emailError,
                        passwordError = passwordError,
                        confirmPasswordError = confirmPasswordError
                    )
                }
                return@requireState
            }

            // Check if email already exists and register
            execute(
                action = {
                    val emailExists = checkEmailExistsUseCase(state.email)
                    if (emailExists) {
                        Result.failure<RegisteredUser>(Exception("EMAIL_EXISTS"))
                    } else {
                        Result.success(
                            registerUserUseCase(
                                RegisterUserUseCase.Params(
                                    name = state.name,
                                    email = state.email,
                                    password = state.password
                                )
                            )
                        )
                    }
                },
                onLoading = { newState { it.copy(isLoading = true) } },
                onSuccess = { result ->
                    if (result.isFailure) {
                        newState {
                            it.copy(
                                isLoading = false,
                                emailError = RegisterEmailError.ALREADY_EXISTS
                            )
                        }
                    } else {
                        newState { it.copy(isLoading = false) }
                        navigate(RegisterNavEvent.ToHome)
                    }
                },
                onError = {
                    newState { it.copy(isLoading = false) }
                }
            )
        }
    }

    fun toLogin() {
        navigate(RegisterNavEvent.ToLogin)
    }

    private fun validateName(name: String): RegisterNameError? {
        return when {
            name.isBlank() -> RegisterNameError.EMPTY
            name.length < MIN_NAME_LENGTH -> RegisterNameError.TOO_SHORT
            else -> null
        }
    }

    private fun validateEmail(email: String): RegisterEmailError? {
        return when {
            email.isBlank() -> RegisterEmailError.EMPTY
            !EMAIL_REGEX.matches(email) -> RegisterEmailError.INVALID_FORMAT
            else -> null
        }
    }

    private fun validatePassword(password: String): RegisterPasswordError? {
        return when {
            password.isBlank() -> RegisterPasswordError.EMPTY
            password.length < MIN_PASSWORD_LENGTH -> RegisterPasswordError.TOO_SHORT
            !PASSWORD_REGEX.matches(password) -> RegisterPasswordError.WEAK
            else -> null
        }
    }

    private fun validateConfirmPassword(password: String, confirmPassword: String): RegisterConfirmPasswordError? {
        return when {
            confirmPassword.isBlank() -> RegisterConfirmPasswordError.EMPTY
            confirmPassword != password -> RegisterConfirmPasswordError.MISMATCH
            else -> null
        }
    }

    companion object {
        private const val MIN_NAME_LENGTH = 2
        private const val MIN_PASSWORD_LENGTH = 8

        private val EMAIL_REGEX = Regex(
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
        )

        private val PASSWORD_REGEX = Regex(
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,}$"
        )
    }
}

enum class RegisterNameError {
    EMPTY,
    TOO_SHORT
}

enum class RegisterEmailError {
    EMPTY,
    INVALID_FORMAT,
    ALREADY_EXISTS
}

enum class RegisterPasswordError {
    EMPTY,
    TOO_SHORT,
    WEAK
}

enum class RegisterConfirmPasswordError {
    EMPTY,
    MISMATCH
}

data class RegisterUiState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val nameError: RegisterNameError? = null,
    val emailError: RegisterEmailError? = null,
    val passwordError: RegisterPasswordError? = null,
    val confirmPasswordError: RegisterConfirmPasswordError? = null,
    val isLoading: Boolean = false,
)

sealed interface RegisterNavEvent : NavEvent {
    data object ToHome : RegisterNavEvent
    data object ToLogin : RegisterNavEvent
}
