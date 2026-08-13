package com.abhi.amplify_store.presentation.product_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.abhi.amplify_store.domain.model.Product
import com.abhi.amplify_store.presentation.utils.components.shared.LabelledText
import com.abhi.amplify_store.presentation.utils.components.shared.MI_Button
import com.abhi.amplify_store.presentation.utils.components.shared.MI_RatingBar
import com.abhi.amplify_store.presentation.utils.components.shared.MI_Tags
import com.abhi.amplify_store.presentation.utils.components.shared.MI_ViewPager

@Composable
fun ProductDetailsContent(
    product: Product,
    modifier: Modifier = Modifier
) {

    Column(
        verticalArrangement = Arrangement.Top,
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
    ) {

        MI_ViewPager(
            images = product.galleryImages,
            modifier = Modifier
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = product.name,
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onBackground
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "$${product.price}",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                MI_RatingBar(
                    product.rating
                )
            }

            LabelledText(
                "In Stock",
                product.stock.toString()
            )

            HorizontalDivider(
                modifier = Modifier.padding(vertical = 3.dp)
            )

            MI_Tags(product.tags)

            Text(
                "Description: ", style = TextStyle(
                    fontWeight = FontWeight.Bold
                )
            )

            Text(
                product.desc, style = TextStyle(
                    color = Color.DarkGray.copy(alpha = 0.9f)
                )
            )
        }
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize()
            .padding(20.dp),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            text = "$${product.price}",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        MI_Button(
            text = "Buy",
            onClick = {},
        )
    }
}


@Preview(showBackground = true)
@Composable
fun ProductDetailsPreview() {
    ProductDetailsContent(
        product = Product(
            id = 1,
            name = "Mobile Phone",
            price = 10.0f,
            stock = 12,
            imageUrl = "",
            desc = "Latest high-end mobile phone featuring dynamic displays and high battery performance.",
            rating = 2.8f,
            discountPrice = 9.0f,
            category = "Electronics",
            reviewCount = 5,
            isFeatured = true,
            isNewArrival = true,
            galleryImages = emptyList(),
            tags = listOf("5G", "Flagship", "AMOLED", "Sale"),
            specifications = emptyMap()
        )
    )
}