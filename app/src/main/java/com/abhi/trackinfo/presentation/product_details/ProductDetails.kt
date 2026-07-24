package com.abhi.trackinfo.presentation.product_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.abhi.trackinfo.domain.model.Product
import com.abhi.trackinfo.domain.usecase.util.Result
import com.abhi.trackinfo.presentation.utils.components.shared.LabelledText
import com.abhi.trackinfo.presentation.utils.components.shared.MI_RatingBar
import com.abhi.trackinfo.presentation.utils.components.shared.MI_TopHeader
import com.abhi.trackinfo.presentation.utils.components.shared.MI_ViewPager

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetails(
    productId: String? = "",
    onBackClick: () -> Unit = {},
    onCartClick: () -> Unit = {},
    viewModel: ProductViewModel = hiltViewModel()
) {
    val id = productId?.toIntOrNull()
    val selectedState by viewModel.selectedProduct.collectAsStateWithLifecycle()

    // Trigger data fetch when productId changes or enters composition
    LaunchedEffect(productId) {
        if (id != null) {
            viewModel.fetchProductById(id)
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = ScaffoldDefaults.contentWindowInsets,
        topBar = {
            MI_TopHeader(
                title = "Product Details",
                cartClick = onCartClick,
                backClick = onBackClick
            )
        }
    ) { innerPadding ->
        when (val state = selectedState) {
            null -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is Result.Success -> {
                ProductDetailsContent(
                    product = state.data,
                    modifier = Modifier.padding(innerPadding)
                )
            }

            is Result.Error -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = state.message,
                        color = Color.Red
                    )
                }
            }
        }
    }
}

@Composable
private fun ProductDetailsContent(
    product: Product,
    modifier: Modifier = Modifier
) {

    val images = listOf(
        "https://picsum.photos/id/30/300/200",
        "https://picsum.photos/id/35/300/200",
        "https://picsum.photos/id/32/300/200",
        "https://picsum.photos/id/33/300/200",
        "https://picsum.photos/id/34/300/200",
    )

    Column(
        verticalArrangement = Arrangement.Top,
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(top = 16.dp, start = 4.dp, end = 4.dp, bottom = 16.dp)
    ) {

        MI_ViewPager(
            images = images,
            modifier = Modifier
        )

        Spacer(Modifier.height(5.dp))

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 16.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceEvenly
            ) {

                Text(
                    text = product.name,
                    style = TextStyle(
                        fontSize = 30.sp
                    ),
                    color = Color.Black
                )

                Spacer(Modifier.height(15.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp, end = 5.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("$ ${product.price}")
                    MI_RatingBar(
                        product.rating
                    )
                }

                Spacer(Modifier.height(10.dp))

                LabelledText(
                    "In Stock",
                    product.stock.toString()
                )

                Spacer(Modifier.height(15.dp))

                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 3.dp)
                )

                Spacer(Modifier.height(15.dp))

                Text("Description: ", style = TextStyle(
                    fontWeight = FontWeight.Bold
                ))

                Spacer(Modifier.height(5.dp))

                Text(product.desc, style = TextStyle(
                    color = Color.DarkGray.copy(alpha = 0.9f)
                ))

            }

        }


    }
}

@Preview(showBackground = true)
@Composable
fun ProductDetailsPreview() {
    ProductDetailsContent(
        product = Product(
            id = 1,
            name = "Mobile",
            price = 10.0f,
            stock = 12,
            imageUrl = "",
            desc = "Latest Mobile",
            rating = 2.8f
        )
    )
}