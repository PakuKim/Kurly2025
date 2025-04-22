package kr.co.kurly.feature.main

import android.os.Parcelable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kr.co.kurly.core.ui.base.BaseViewModel
import kr.co.kurly.domain.interactor.FetchProductLikedIdsUseCase
import kr.co.kurly.domain.interactor.FetchProductListUseCase
import kr.co.kurly.domain.interactor.LoadProductUseCase
import kr.co.kurly.domain.interactor.UpdateProductLikedUseCase
import kr.co.kurly.domain.model.ProductSection
import javax.inject.Inject

@HiltViewModel
internal open class MainViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    fetchProductLikedIdsUseCase: FetchProductLikedIdsUseCase,
    fetchProductListUseCase: FetchProductListUseCase,
    private val loadProductUseCase: LoadProductUseCase,
    private val updateProductLikedUseCase: UpdateProductLikedUseCase,
) : BaseViewModel<MainViewModel.State>(savedStateHandle) {

    private val _isRefreshing: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing.asStateFlow()

    private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    fun onLiked(id: Long, isLiked: Boolean) = launch {
        updateProductLikedUseCase(id, isLiked)
    }

    fun refresh() = launch {
        setRefreshing(true)
        loadProductUseCase(true)
    }

    fun load() = launch {
        if (currentState.hasMore && !isLoading.value) {
            setLoading(true)
            loadProductUseCase(false)
        }
    }

    private fun setRefreshing(isRefreshing: Boolean) = viewModelScope.launch {
        _isRefreshing.emit(isRefreshing)
    }

    private fun setLoading(isLoading: Boolean) = viewModelScope.launch {
        _isLoading.emit(isLoading)
    }

    override fun setError(errorMsg: String): Job {
        setLoading(false)
        setRefreshing(false)
        return super.setError(errorMsg)
    }

    init {
        launch {
            fetchProductLikedIdsUseCase().collectLatest {
                updateState {
                    copy(likedIds = it)
                }
            }
        }

        launch {
            fetchProductListUseCase().collectLatest {
                setRefreshing(false)
                setLoading(false)
                updateState {
                    copy(
                        hasMore = it.first,
                        products = it.second.toMutableStateList()
                    )
                }
            }
        }
    }

    override fun createInitialState(savedState: Parcelable?): State {
        return State()
    }

    data class State(
        val likedIds: Set<Long> = emptySet(),
        val products: SnapshotStateList<ProductSection> = mutableStateListOf(),
        val hasMore: Boolean = false,
    ) : BaseViewModel.State
}