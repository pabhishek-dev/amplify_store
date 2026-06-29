package com.abhi.trackinfo.presentation.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.MenuOpen
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.abhi.trackinfo.presentation.profile.components.GridItem
import com.abhi.trackinfo.presentation.profile.components.ListItem

@Composable
fun Profile(
    onNavigate: () -> Unit = {}
) {

    val listItems = listOf(
        "My Vouchers",
        "Statements",
        "Cards",
        "Address",
        "Favourites",
        "Rewards"
    )

    Scaffold(
        topBar = {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color.Transparent
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(),
                shape = RoundedCornerShape(
                    bottomStart = 15.dp,
                    bottomEnd = 15.dp,
                )

            ) {
                Box(
                    modifier = Modifier
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(Color(0xFF6200EE), Color(0xFF9864D7))
                            )
                        )
                        .semantics { testTag = "ProfileTopBox" }
                        .padding(16.dp)
                        .fillMaxSize()
                ) {

                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = "back btn",
                        tint = Color.White,
                        modifier = Modifier
                            .clickable{
                                onNavigate()
                            }
                    )

                    Icon(
                        imageVector = Icons.AutoMirrored.Default.MenuOpen,
                        contentDescription = "More btn",
                        tint = Color.White,
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                    )

                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                    ) {

                        Text(
                            "Abhishek",
                            modifier = Modifier.padding(3.dp),
                            style = TextStyle(
                                color = Color.White
                            ),
                        )
                        Text(
                            "+91-9876543219",
                            modifier = Modifier.padding(2.dp),
                            style = TextStyle(
                                color = Color.LightGray,
                                fontSize = 11.sp
                            ),
                        )
                        Text(
                            "Abhishek@gmail.com",
                            modifier = Modifier.padding(2.dp),
                            style = TextStyle(
                                color = Color.LightGray,
                                fontSize = 11.sp
                            ),
                        )
                    }
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxWidth()
        ) {

            Row(
                modifier = Modifier
            ) {
                LazyHorizontalGrid(
                    rows = GridCells.Fixed(1),
                    modifier = Modifier
                        .padding(8.dp)
                        .height(90.dp)
                ) {
                    items(
                        count = 3
                    ) { index ->
                        GridItem(
                            modifier = Modifier
                                .padding(5.dp)
                        )
                    }
                }
            }
            LazyColumn(
                modifier = Modifier
                    .semantics { testTag = "ProfileList" }
                    .padding(13.dp)
                    .border(
                        border = BorderStroke(1.dp, color = Color.LightGray),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .fillMaxWidth()
            ) {
                items(
                    count = listItems.size,
                    key = { index -> listItems[index] },
                ) { index ->
                    ListItem(
                        tileName = listItems[index],
                        tileLogo = 0
                    )
                    if(index != listItems.lastIndex){
                        HorizontalDivider(
                            color = Color.LightGray,
                            thickness = 0.6.dp
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ProfileScreen(
    onNavClick: () -> Unit
) {
    Profile(
        onNavigate = { onNavClick() }
    )
}

@Preview
@Composable
fun ProfilePreview() {
    Profile()
}
