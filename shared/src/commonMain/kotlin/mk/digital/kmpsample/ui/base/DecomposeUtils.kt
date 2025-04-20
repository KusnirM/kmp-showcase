package mk.digital.kmpsample.ui.base

import com.arkivanov.essenty.lifecycle.LifecycleOwner
import com.arkivanov.essenty.lifecycle.doOnDestroy
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlin.coroutines.CoroutineContext

fun LifecycleOwner.coroutineScope(
    context: CoroutineContext = Dispatchers.Main.immediate,
): CoroutineScope {
    val scope = CoroutineScope(context + SupervisorJob())
    lifecycle.doOnDestroy(scope::cancel)

    return scope
}
//
//fun <T : Any> StateFlow<T>.asValue(
//    lifecycle: Lifecycle,
//    context: CoroutineContext = Dispatchers.Main.immediate,
//): Value<T> =
//    asValue(
//        initialValue = value,
//        lifecycle = lifecycle,
//        context = context,
//    )
//
//fun <T : Any> Flow<T>.asValue(
//    initialValue: T,
//    lifecycle: Lifecycle,
//    context: CoroutineContext = Dispatchers.Main.immediate,
//): Value<T> {
//    val value = MutableValue(initialValue)
//    var scope: CoroutineScope? = null
//
//    lifecycle.subscribe(
//        onStart = {
//            scope = CoroutineScope(context).apply {
//                launch {
//                    collect { value.value = it }
//                }
//            }
//        },
//        onStop = {
//            scope?.cancel()
//            scope = null
//        }
//    )
//
//    return value
//}
