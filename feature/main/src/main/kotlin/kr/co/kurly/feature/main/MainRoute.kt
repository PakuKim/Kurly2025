package kr.co.kurly.feature.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.text.style.TextDecoration
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
import kr.co.kurly.domain.model.ProductType
import timber.log.Timber

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MainRoute(
    viewModel: MainViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    var isLoading by remember { mutableStateOf(false) }

    LaunchedEffect(viewModel.isLoading) {
        viewModel.isLoading.collectLatest {
            isLoading = it
            Timber.d("isLoading: $it")
        }
    }

    MainScreen(
        state = state,
        isLoading = isLoading,
        onRefresh = viewModel::refresh,
        onLoadMore = viewModel::load
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MainScreen(
    state: MainViewModel.State = MainViewModel.State(),
    isLoading: Boolean = false,
    onRefresh: () -> Unit = {},
    onLoadMore: () -> Unit = {},
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(title = { Text("Main") })
        }
    ) { scaffoldPadding ->
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize()
                .padding(scaffoldPadding)
                .padding(horizontal = 16.dp),
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            state.products.forEach { section ->
                item(
                    span = { GridItemSpan(3) }
                ) {
                    MainSectionScreen(
                        title = section.title
                    )
                }

                when (section.type) {
                    ProductType.VERTICAL -> {
                        items(
                            items = section.products,
                            span = { GridItemSpan(3) }
                        ) { product ->
                            MainLargeProductItem(
                                src = product.image,
                                isLiked = state.likedIds.contains(product.id),
                                title = product.name,
                                price = product.originalPrice,
                                originalPrice = product.discountedPrice,
                                saleRatio = product.saleRate
                            )
                        }
                    }

                    ProductType.HORIZONTAL -> {
                        item(
                            span = { GridItemSpan(3) }
                        ) {
                            LazyRow {
                                items(section.products) { product ->
                                    MainSmallProductItem(
                                        src = product.image,
                                        isLiked = state.likedIds.contains(product.id),
                                        title = product.name,
                                        price = product.originalPrice,
                                        originalPrice = product.discountedPrice,
                                        saleRatio = product.saleRate
                                    )
                                }
                            }
                        }
                    }

                    ProductType.GRID -> {
                        items(section.products) { product ->
                            MainSmallProductItem(
                                src = product.image,
                                isLiked = state.likedIds.contains(product.id),
                                title = product.name,
                                price = product.originalPrice,
                                originalPrice = product.discountedPrice,
                                saleRatio = product.saleRate
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
    Spacer(modifier = Modifier.height(16.dp))

    Text(
        text = title,
        style = MainTheme.typography.header01EB
    )

    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
private fun MainSmallProductItem(
    src: String?,
    isLiked: Boolean,
    title: String,
    price: Int,
    originalPrice: Int?,
    saleRatio: Int?
) {
    Column(
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
                    .size(24.dp),
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
            style = MainTheme.typography.header01R
        )

        Text(
            text = buildAnnotatedString {
                saleRatio?.let {
                    withStyle(
                        MainTheme.typography.body01B.toSpanStyle().copy(
                            color = MainColor.orange
                        )
                    ) {
                        append("$it% ")
                    }
                }

                withStyle(MainTheme.typography.body01B.toSpanStyle()) {
                    append(price.toNumberFormat() + "원")
                }
            }
        )

        originalPrice?.let {
            Text(
                text = it.toNumberFormat() + "원",
                style = MainTheme.typography.body01R.copy(
                    color = MainColor.grey
                ),
                textDecoration = TextDecoration.LineThrough
            )
        }
    }
}

@Composable
private fun MainLargeProductItem(
    src: String?,
    isLiked: Boolean,
    title: String,
    price: Int,
    originalPrice: Int?,
    saleRatio: Int?
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
                    .size(24.dp),
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
            style = MainTheme.typography.header01R
        )

        Text(
            text = buildAnnotatedString {
                saleRatio?.let {
                    withStyle(
                        MainTheme.typography.body01B.toSpanStyle().copy(
                            color = MainColor.orange
                        )
                    ) {
                        append("$it% ")
                    }
                }

                withStyle(MainTheme.typography.body01B.toSpanStyle()) {
                    append(price.toNumberFormat() + "원")
                }

                originalPrice?.let {
                    withStyle(
                        MainTheme.typography.body01R.toSpanStyle().copy(
                            fontSize = 13.sp,
                            textDecoration = TextDecoration.LineThrough
                        )
                    ) {
                        append(it.toNumberFormat() + "원")
                    }
                }
            }
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
        MainSmallProductItem(
            src = null,
            isLiked = true,
            title = "TEST",
            price = 10000,
            originalPrice = 10000,
            saleRatio = 5
        )
    }
}

@Preview
@Composable
private fun MainLargeProductPreview() {
    MainTheme {
        MainLargeProductItem(
            src = null,
            isLiked = true,
            title = "TEST",
            price = 10000,
            originalPrice = 10000,
            saleRatio = 5
        )
    }
}