package kr.co.kurly

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import kr.co.kurly.core.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
internal class MainViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
):BaseViewModel<MainViewModel.State>(savedStateHandle) {

    override fun createInitialState(savedState: Parcelable?): State {
        return State
    }

    object State: BaseViewModel.State
}