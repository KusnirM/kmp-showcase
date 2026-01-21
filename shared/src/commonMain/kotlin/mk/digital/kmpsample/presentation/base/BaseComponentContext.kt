package mk.digital.kmpsample.presentation.base

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import mk.digital.kmpsample.util.Logger

/**
 * Toolbar interface for screens that need a top bar.
 */
interface Toolbar {
    @Composable
    fun toolbarTitle(): String
    val navIcon: ImageVector? get() = Icons.AutoMirrored.Filled.ArrowBack
    val onBack: () -> Unit get() = {}
}

/**
 * Base interface for all screen ViewModels.
 */
interface BaseComponent {
    fun loadInitialData() {}
    fun onResumed() {}
    fun onCreated() {}
    fun onPaused() {}
}

/**
 * Interface for screens that have both BaseScreen and Toolbar capabilities.
 */
interface BaseToolbarComponent : BaseComponent, Toolbar


abstract class BaseComponentContext<STATE : Any>(
    defaultState: STATE,
) : ViewModel(), BaseToolbarComponent {

    protected val tag = this::class.simpleName
    private var isInitialized = false

    private val _state: MutableStateFlow<STATE> = MutableStateFlow(defaultState)
    val state: StateFlow<STATE> = _state.asStateFlow()

    /**
     * Called to load initial data. Override this in ViewModels.
     * Only called once per ViewModel lifecycle, unless resetState() is called.
     */
    override fun loadInitialData() {
        // Default implementation does nothing
    }


    private val _navEvent = MutableSharedFlow<NavEvent>()
    val navEvent: SharedFlow<NavEvent> = _navEvent.asSharedFlow()

    protected val scope get() = viewModelScope

    /**
     * Emits a navigation event to be handled by the UI layer.
     * @param event The navigation event (typically a sealed class/interface instance)
     */
    protected fun navigate(event: NavEvent) {
        viewModelScope.launch { _navEvent.emit(event) }
    }

    /**
     * Updates the state using a copy function.
     */
    protected fun newState(stateCopy: (STATE) -> STATE) {
        val oldState = _state.value
        _state.value = stateCopy(oldState)
    }

    /**
     * Executes a block with the current state.
     */
    protected fun requireState(block: (STATE) -> Unit): Unit = block(_state.value)

    /**
     * Returns the current state value.
     */
    protected fun requireState(): STATE = _state.value

    /**
     * Logs the screen name for analytics.
     */
    protected fun logScreenName() {
        Logger.d("Screen Name: ${tag?.removeSuffix("Component")}Screen")
    }

    /**
     * Called when the screen is first created.
     * Override to perform one-time setup.
     */
    override fun onCreated() {
        if (!isInitialized) {
            isInitialized = true
            loadInitialData()
            logScreenName()
        }
    }

    /**
     * Called when the screen is resumed/becomes visible.
     * Loads initial data only once (unless resetState() was called).
     */
    override fun onResumed() {

    }

    /**
     * Called when the screen is paused/becomes invisible.
     * Override to perform cleanup or save state.
     */
    override fun onPaused() {}
}

interface NavEvent
