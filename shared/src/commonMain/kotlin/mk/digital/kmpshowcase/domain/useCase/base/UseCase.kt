package mk.digital.kmpshowcase.domain.useCase.base

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

/**
 * Base class for all use cases.
 * Encapsulates a single business logic operation.
 * Automatically runs on IO dispatcher.
 *
 * @param Params Input parameters for the use case
 * @param Result Output result from the use case
 */
abstract class UseCase<in Params, out Result> {

    /**
     * Executes the use case logic.
     * Override this in concrete implementations.
     */
    protected abstract suspend fun run(params: Params): Result

    /**
     * Invokes the use case on IO dispatcher.
     */
    suspend operator fun invoke(params: Params): Result = withContext(Dispatchers.IO) {
        run(params)
    }
}

/**
 * Extension for use cases that don't require parameters.
 */
suspend operator fun <T> UseCase<None, T>.invoke(): T = invoke(None)

/**
 * Marker object for use cases without parameters.
 */
object None
