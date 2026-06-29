package com.abhi.trackinfo.presentation.home.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.abhi.trackinfo.R
import com.abhi.trackinfo.domain.model.Product
import com.abhi.trackinfo.presentation.utils.UiConstants.baseTextStyle
import com.abhi.trackinfo.presentation.utils.components.shared.MI_RatingBar

@Composable
fun ProductViewCard(
    product: Product,
    isFavourite: Boolean,
    click: (Int) -> Unit,
    onFav: (Int) -> Unit
) {

    Card(
        onClick = { click(product.id) },
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
            .padding(20.dp),
        colors = CardDefaults.cardColors(Color(0xFFFFFFFF)),
        elevation = CardDefaults.elevatedCardElevation(20.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            AsyncImage(
                model = product.imageUrl,
                modifier = Modifier
                    .weight(3f)
                    .fillMaxHeight()
                    .background(Color.DarkGray),
                contentDescription = "product image",
                contentScale = ContentScale.Crop,
                onError = {
                    Log.e("CoilImage", "URL: ${product.imageUrl}")
                    Log.e(
                        "CoilImage",
                        "Error: ${it.result.throwable}",
                        it.result.throwable
                    )
                }
            )


            Column(
                modifier = Modifier
                    .weight(2f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        product.name,
                        style = baseTextStyle.copy(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily(Font(R.font.opensans_semibold))
                        )
                    )

                    Text(
                        "${product.stock} pc",
                        style = baseTextStyle.copy(fontSize = 10.sp),
                        fontFamily = FontFamily(Font(R.font.opensans_light))
                    )
                }



                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Column {

                        MI_RatingBar(product.rating, 3.dp, 14.dp)

                        Text(
                            "$${product.price}",
                            modifier = Modifier
                                .padding(5.dp),
                            textAlign = TextAlign.Right,
                            style = baseTextStyle.copy(
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily(Font(R.font.opensans_light))
                            )
                        )
                    }

                    FavouriteIcon(
                        isFavourite = isFavourite,
                        onToggle = { onFav(product.id) }
                    )

                }

                Spacer(Modifier.weight(1f))

            }
        }
    }
}

@Preview
@Composable
fun ProductViewCardPreview() {
    ProductViewCard(
        product = Product(
            id = 1,
            name = "Test",
            price = 12f,
            stock = 2,
            imageUrl = "",
            desc = "",
            rating = 2f
        ),
        isFavourite = true,
        click = {},
        onFav = {},
    )
}
