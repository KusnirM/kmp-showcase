package mk.digital.kmpsample.presentation.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class UseCaseRunner {
    operator fun <Result> invoke(
        job: CoroutineScope,
        action: suspend () -> Result,
        onError: (Throwable) -> Unit = {},
        onSuccess: suspend (Result) -> Unit = {},
        preAction: suspend () -> Unit = {},
    ) = job.launch {
        try {
            preAction()
            onSuccess(action())
        } catch (e: Throwable) {
            println(e)
            onError(e)
        }
    }
}
