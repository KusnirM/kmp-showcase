package mk.digital.kmpsample.domain.useCase.base

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

abstract class UseCase<Params, Result> {

    abstract suspend fun run(params: Params): Result

    suspend operator fun invoke(params: Params, isAsync: Boolean = true): Result = coroutineScope {
        return@coroutineScope if (isAsync) {
            val job = async { run(params) }
            job.await()
        } else {
            run(params)
        }
    }
}

suspend operator fun <T> UseCase<None, T>.invoke(isAsync: Boolean = true): T = invoke(None, isAsync)

object None
