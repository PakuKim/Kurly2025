package kr.co.kurly.feature.main

import android.os.Parcelable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kr.co.kurly.core.ui.base.BaseViewModel
import kr.co.kurly.domain.interactor.FetchProductLikedIdsUseCase
import kr.co.kurly.domain.interactor.FetchProductListUseCase
import kr.co.kurly.domain.interactor.LoadProductUseCase
import kr.co.kurly.domain.model.ProductSection
import javax.inject.Inject

@HiltViewModel
internal class MainViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    fetchProductLikedIdsUseCase: FetchProductLikedIdsUseCase,
    fetchProductListUseCase: FetchProductListUseCase,
    private val loadProductUseCase: LoadProductUseCase
) : BaseViewModel<MainViewModel.State>(savedStateHandle) {

    fun refresh() = launchWithLoading {
        loadProductUseCase.invoke(1)
    }

    fun load() = launch {
        loadProductUseCase()
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
                updateState {
                    copy(products = it.toMutableStateList())
                }
            }
        }
    }

    override fun createInitialState(savedState: Parcelable?): State {
        return State()
    }

    data class State(
        val likedIds: Set<Long> = emptySet(),
        val products: SnapshotStateList<ProductSection> = mutableStateListOf()
    ) : BaseViewModel.State
}