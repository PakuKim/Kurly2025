package kr.co.kurly.core.ui.base

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.updateAndGet
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel<STATE : BaseViewModel.State>(
    private val stateHandle: SavedStateHandle,
) : ViewModel() {

    private val initialState by lazy {
        createInitialState(stateHandle[KEY_STATE])
    }

    private var _currentState: STATE? = null
        set(value) {
            field = value
            if (value != null) {
                stateHandle[KEY_STATE] = value.toParcelable()
            }
        }

    val currentState: STATE
        get() {
            return _currentState ?: this.initialState
        }

    private val _state: MutableStateFlow<STATE> = MutableStateFlow(initialState)
    val state: StateFlow<STATE> = _state.asStateFlow()

    private val _error: MutableSharedFlow<String> = MutableSharedFlow()
    val error: SharedFlow<String> = _error.asSharedFlow()

    private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    protected abstract fun createInitialState(savedState: Parcelable?): STATE

    protected val ceh = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
        setError(throwable.localizedMessage ?: "")
        setLoading(false)
    }

    protected inline fun launch(
        coroutineContext: CoroutineContext = ceh,
        @ViewModelScoped crossinline action: suspend CoroutineScope.() -> Unit,
    ): Job {
        return viewModelScope.launch(coroutineContext) {
            action(this)
        }
    }

    protected fun launchWithLoading(
        coroutineContext: CoroutineContext = ceh,
        action: suspend CoroutineScope.() -> Unit,
    ): Job {
        return viewModelScope.launch(coroutineContext) {
            setLoading(true).join()
            action(this)
            setLoading(false).join()
        }
    }

    protected fun updateState(action: STATE.() -> STATE) {
        try {
            _state.updateAndGet(action)
                .also {
                    _currentState = it
                }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun setError(errorMsg: String) = viewModelScope.launch {
        _error.emit(errorMsg)
    }

    protected fun setLoading(isVisible: Boolean) = viewModelScope.launch {
        _isLoading.emit(isVisible)
    }

    protected inline fun <reified T : STATE, K : Any?> Flow<T>.select(
        crossinline selector: (state: T) -> K,
    ): Flow<K> {
        return this.distinctUntilChangedBy { state: T -> selector.invoke(state) }
            .map { state: T -> selector.invoke(state) }
    }

    protected fun <T : Any?> Flow<T>.bindState(to: MutableStateFlow<T>) {
        this.onEach(to::emit).launchIn(viewModelScope + ceh)
    }

    protected fun <T : Any?> Flow<T>.bindShared(to: MutableSharedFlow<T>) {
        this.onEach(to::emit).launchIn(viewModelScope + ceh)
    }

    interface State {
        fun toParcelable(): Parcelable? = null
    }

    companion object {
        const val KEY_STATE = "keyState"
    }
}