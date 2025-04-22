package kr.co.kurly.core.ui.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filterNotNull

@Composable
fun InfiniteLazyVerticalGrid(
    modifier: Modifier = Modifier,
    listState: LazyGridState = rememberLazyGridState(),
    columns: GridCells,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    contentPadding: PaddingValues = PaddingValues(),
    reverseLayout: Boolean = false,
    onLoadMore: () -> Unit,
    content: LazyGridScope.() -> Unit,
) {
    var isLoading by remember { mutableStateOf(true) }
    var prevTotalItemCount by remember { mutableIntStateOf(0) }

    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull() }
            .filterNotNull()
            .collectLatest { last ->
                val totalItemCount = listState.layoutInfo.totalItemsCount

                if (!isLoading && last.index >= (totalItemCount - 10)) {
                    onLoadMore()
                    isLoading = true
                }

                if (totalItemCount < prevTotalItemCount) {
                    prevTotalItemCount = totalItemCount
                    isLoading = (totalItemCount == 0)
                }

                if (isLoading && (totalItemCount > prevTotalItemCount)) {
                    isLoading = false
                    prevTotalItemCount = totalItemCount
                }
            }
    }

    LazyVerticalGrid(
        modifier = modifier,
        columns = columns,
        contentPadding = contentPadding,
        state = listState,
        verticalArrangement = verticalArrangement,
        horizontalArrangement = horizontalArrangement,
        reverseLayout = reverseLayout,
        content = content
    )
}