package kr.co.kurly.feature.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import kotlinx.coroutines.flow.collectLatest
import kr.co.kurly.common.ext.toNumberFormat
import kr.co.kurly.core.ui.theme.MainColor
import kr.co.kurly.core.ui.theme.MainTheme
import kr.co.kurly.core.ui.widget.InfiniteLazyVerticalGrid
import kr.co.kurly.core.ui.widget.KurlySnackBarHost
import kr.co.kurly.core.ui.widget.LoadingScreen
import kr.co.kurly.domain.model.ProductType

@Composable
internal fun MainRoute(
    viewModel: MainViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val snackBarHostState = remember { SnackbarHostState() }

    var isLoading by remember { mutableStateOf(false) }
    var isRefreshing by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.isLoading.collectLatest {
            isLoading = it
        }
    }

    LaunchedEffect(Unit) {
        viewModel.isRefreshing.collectLatest {
            isRefreshing = it
        }
    }

    LaunchedEffect(Unit) {
        viewModel.error.collectLatest {
            snackBarHostState.showSnackbar(
                message = it,
                duration = SnackbarDuration.Short
            )
        }
    }

    LoadingScreen(isLoading = state.products.isEmpty()) {
        MainScreen(
            state = state,
            snackBarHostState = snackBarHostState,
            isLoading = isLoading,
            isRefreshing = isRefreshing,
            onRefresh = viewModel::refresh,
            onLoadMore = viewModel::load,
            onLikeClick = viewModel::onLiked
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MainScreen(
    state: MainViewModel.State = MainViewModel.State(),
    snackBarHostState: SnackbarHostState = remember { SnackbarHostState() },
    isLoading: Boolean = false,
    isRefreshing: Boolean = false,
    onRefresh: () -> Unit = {},
    onLoadMore: () -> Unit = {},
    onLikeClick: (Long, Boolean) -> Unit = { _, _ -> }
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Kurly",
                        style = MainTheme.typography.header01EB,
                        fontStyle = FontStyle.Italic,
                        color = MainColor.keyColor
                    )
                }
            )
        },
        snackbarHost = { KurlySnackBarHost(snackBarHostState) }
    ) { scaffoldPadding ->
        PullToRefreshBox(
            modifier = Modifier
                .fillMaxSize()
                .padding(scaffoldPadding),
            isRefreshing = isRefreshing,
            onRefresh = onRefresh,
        ) {
            InfiniteLazyVerticalGrid(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                columns = GridCells.Fixed(3),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                onLoadMore = onLoadMore
            ) {
                state.products.forEachIndexed { index, section ->
                    item(
                        span = { GridItemSpan(maxLineSpan) }
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(20.dp)
                        ) {
                            if (index != 0) {
                                HorizontalDivider()
                            }

                            MainSectionScreen(
                                title = section.title
                            )
                        }
                    }

                    when (section.type) {
                        ProductType.VERTICAL -> {
                            items(
                                items = section.products,
                                span = { GridItemSpan(maxLineSpan) }
                            ) { product ->
                                MainLargeProductItem(
                                    id = product.id,
                                    src = product.image,
                                    isLiked = state.likedIds.contains(product.id),
                                    title = product.name,
                                    price = product.originalPrice,
                                    discountedPrice = product.disCountedPrice,
                                    saleRate = product.saleRate,
                                    onLikeClick = onLikeClick
                                )
                            }
                        }

                        ProductType.HORIZONTAL -> {
                            item(
                                span = { GridItemSpan(maxLineSpan) }
                            ) {
                                LazyRow(
                                    modifier = Modifier
                                        .wrapContentHeight(),
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    items(section.products) { product ->
                                        MainSmallProductItem(
                                            id = product.id,
                                            src = product.image,
                                            isLiked = state.likedIds.contains(product.id),
                                            title = product.name,
                                            price = product.originalPrice,
                                            discountedPrice = product.disCountedPrice,
                                            saleRate = product.saleRate,
                                            onLikeClick = onLikeClick
                                        )
                                    }
                                }
                            }
                        }

                        ProductType.GRID -> {
                            items(section.products.take(6)) { product ->
                                MainSmallProductItem(
                                    id = product.id,
                                    src = product.image,
                                    isLiked = state.likedIds.contains(product.id),
                                    title = product.name,
                                    price = product.originalPrice,
                                    discountedPrice = product.disCountedPrice,
                                    saleRate = product.saleRate,
                                    onLikeClick = onLikeClick
                                )
                            }
                        }
                    }
                }
                if (isLoading) {
                    item(
                        span = { GridItemSpan(maxLineSpan) }
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(40.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun MainSectionScreen(
    title: String
) {
    Text(
        text = title,
        style = MainTheme.typography.header01EB
    )
}

@Composable
private fun MainSmallProductItem(
    id: Long,
    src: String?,
    isLiked: Boolean,
    title: String,
    price: Int,
    discountedPrice: Int?,
    saleRate: Int?,
    onLikeClick: (Long, Boolean) -> Unit = { _, _ -> }
) {
    Column(
        modifier = Modifier
            .width(IntrinsicSize.Min),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Box {
            AsyncImage(
                modifier = Modifier
                    .size(
                        width = 150.dp,
                        height = 200.dp
                    ),
                model = src,
                contentDescription = "Product Image",
                contentScale = ContentScale.FillBounds,
            )

            Image(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(12.dp)
                    .size(24.dp)
                    .clickable(
                        enabled = true,
                        onClick = { onLikeClick(id, isLiked) }
                    ),
                painter = painterResource(
                    if (isLiked) {
                        kr.co.kurly.core.ui.R.drawable.ic_btn_heart_on
                    } else {
                        kr.co.kurly.core.ui.R.drawable.ic_btn_heart_off
                    }
                ),
                contentDescription = "Product Liked"
            )
        }

        Text(
            text = title,
            style = MainTheme.typography.body01R,
            fontSize = 12.sp,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            text = buildAnnotatedString {
                saleRate?.let {
                    withStyle(
                        MainTheme.typography.body01B.toSpanStyle().copy(
                            color = MainColor.orange
                        )
                    ) {
                        append("$it% ")
                    }
                }

                withStyle(MainTheme.typography.body01B.toSpanStyle()) {
                    if (discountedPrice == null) {
                        append(price.toNumberFormat() + "원")
                    } else {
                        append(discountedPrice.toNumberFormat() + "원")
                    }
                }
            },
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            modifier = Modifier,
            text = if (discountedPrice != null) {
                price.toNumberFormat() + "원"
            } else {
                "-"
            },
            style = MainTheme.typography.body01R.copy(
                color = if (discountedPrice != null) {
                    MainColor.grey
                } else {
                    MainColor.white
                }
            ),
            textDecoration = TextDecoration.LineThrough,
        )
    }
}

@Composable
private fun MainLargeProductItem(
    id: Long,
    src: String?,
    isLiked: Boolean,
    title: String,
    price: Int,
    discountedPrice: Int?,
    saleRate: Int?,
    onLikeClick: (Long, Boolean) -> Unit = { _, _ -> }
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Box {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.5f),
                model = src,
                contentDescription = "Product Image",
                contentScale = ContentScale.Crop,
            )

            Image(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(12.dp)
                    .size(24.dp)
                    .clickable(
                        enabled = true,
                        onClick = { onLikeClick(id, isLiked) }
                    ),
                painter = painterResource(
                    if (isLiked) {
                        kr.co.kurly.core.ui.R.drawable.ic_btn_heart_on
                    } else {
                        kr.co.kurly.core.ui.R.drawable.ic_btn_heart_off
                    }
                ),
                contentDescription = "Product Liked"
            )
        }

        Text(
            text = title,
            style = MainTheme.typography.header01R,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            text = buildAnnotatedString {
                saleRate?.let {
                    withStyle(
                        MainTheme.typography.body01B.toSpanStyle().copy(
                            color = MainColor.orange
                        )
                    ) {
                        append("$it% ")
                    }
                }

                discountedPrice?.let {
                    withStyle(
                        MainTheme.typography.body01B.toSpanStyle()
                    ) {
                        append(it.toNumberFormat() + "원 ")
                    }
                }

                withStyle(
                    if (discountedPrice != null) {
                        MainTheme.typography.body01R.toSpanStyle().copy(
                            textDecoration = TextDecoration.LineThrough
                        )
                    } else {
                        MainTheme.typography.body01B.toSpanStyle()
                    }
                ) {
                    append(price.toNumberFormat() + "원")
                }
            },
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun MainScreenPreview() {
    MainTheme {
        MainScreen()
    }
}

@Preview
@Composable
private fun MainSmallProductPreview() {
    MainTheme {
        Box(Modifier.background(MainColor.white)) {
            MainSmallProductItem(
                id = 0,
                src = null,
                isLiked = true,
                title = "TESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTEST",
                price = Int.MAX_VALUE,
                discountedPrice = Int.MAX_VALUE,
                saleRate = 5
            )
        }
    }
}

@Preview
@Composable
private fun MainLargeProductPreview() {
    MainTheme {
        Box(Modifier.background(MainColor.white)) {
            MainLargeProductItem(
                id = 0,
                src = null,
                isLiked = true,
                title = "TESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTESTTEST",
                price = Int.MAX_VALUE,
                discountedPrice = Int.MAX_VALUE,
                saleRate = 5
            )
        }
    }
}