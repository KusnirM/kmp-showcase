package mk.digital.kmpsample.presentation.base

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.Lifecycle
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import mk.digital.kmpsample.util.Logger


interface BaseComponent {
    fun loadInitialData() {}
    fun onResumed() {}
    fun onCreated() {}
    fun onPaused() {}
}


abstract class BaseComponentContext<STATE : Any>(
    componentContext: ComponentContext,
    defaultState: STATE,
) : BaseComponent, ComponentContext by componentContext {

    protected val tag = this::class.simpleName

    init {
        componentContext.lifecycle.subscribe(
            object : Lifecycle.Callbacks {
                override fun onCreate() {
                    super.onCreate()
                    onCreated()
                }

                override fun onResume() {
                    super.onResume()
                    loadInitialData()
                    logScreenName()
                    onResumed()
                }

                override fun onPause() {
                    super.onPause()
                    onPaused()
                }
            }
        )
    }

    private val _state: MutableStateFlow<STATE> = MutableStateFlow(defaultState)
    val state: StateFlow<STATE> = _state
    protected val scope = coroutineScope()

    protected fun newState(stateCopy: (STATE) -> STATE) {
        val oldState = _state.value
        _state.value = stateCopy(oldState)
    }

    protected fun requireState(block: (STATE) -> Unit): Unit = block(_state.value)
    protected fun requireState(): STATE = _state.value

    private fun logScreenName() {
        Logger.d("Screen Name: ${tag?.removeSuffix("ComponentImpl")}Screen")
    }
}
