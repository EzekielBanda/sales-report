package com.unitech.learning.screen.home

 import androidx.compose.foundation.background
 import androidx.compose.foundation.layout.Box
 import androidx.compose.foundation.layout.Column
 import androidx.compose.foundation.layout.fillMaxSize
 import androidx.compose.foundation.layout.padding
 import androidx.compose.material3.MaterialTheme
 import androidx.compose.material3.Text
 import androidx.compose.runtime.Composable
 import androidx.compose.ui.Alignment
 import androidx.compose.ui.Modifier
 import androidx.compose.ui.tooling.preview.Preview
 import androidx.compose.ui.unit.dp
 import com.unitech.learning.R
 import com.unitech.learning.model.BottomNavigation
 import com.unitech.learning.model.PopularProduct
 import com.unitech.learning.ui.theme.Beige1
 import com.unitech.learning.ui.theme.Beige2
 import com.unitech.learning.ui.theme.Beige3
 import com.unitech.learning.ui.theme.BlueViolet1
 import com.unitech.learning.ui.theme.BlueViolet2
 import com.unitech.learning.ui.theme.BlueViolet3
 import com.unitech.learning.ui.theme.DeepBlue
 import com.unitech.learning.ui.theme.LightGreen1
 import com.unitech.learning.ui.theme.LightGreen2
 import com.unitech.learning.ui.theme.LightGreen3
 import com.unitech.learning.ui.theme.OrangeYellow1
 import com.unitech.learning.ui.theme.OrangeYellow2
 import com.unitech.learning.ui.theme.OrangeYellow3
 import com.unitech.learning.ui.theme.TextWhite

@Preview
@Composable
fun HomeScreen() {
    Box (modifier = Modifier
        .background(DeepBlue)
        .fillMaxSize()
    ){

        Column {
            //Top Bar Section
            TopBarSection()
            Text(
                text = "Categories",
                modifier = Modifier
                    .padding(start = 15.dp),
                style = MaterialTheme.typography.headlineSmall,
                color = TextWhite
            )
            //Category Section
            CategorySection(
                categories = listOf(
                    "Shoes", "Clothes",
                    "Bags", "Electronics",
                    "Watches", "Ornaments"
                )
            )
            //Current Sales
            CurrentSales()

            PopularProducts(
                products = listOf(
                    PopularProduct(
                        title = "Shoes",
                        R.drawable.ic_headphone,
                        BlueViolet1,
                        BlueViolet2,
                        BlueViolet3
                    ),
                    PopularProduct(
                        title = "Clothes",
                        R.drawable.ic_videocam,
                        LightGreen1,
                        LightGreen2,
                        LightGreen3
                    ),

                    PopularProduct(
                        title = "Bags",
                        R.drawable.ic_headphone,
                        OrangeYellow1,
                        OrangeYellow2,
                        OrangeYellow3
                    ),
                    PopularProduct(
                        title = "Electronics",
                        R.drawable.ic_videocam,
                        Beige1,
                        Beige2,
                        Beige3
                    )
                )
            )
        }
        BottomNavigationBar(
            items = listOf(
                BottomNavigation("Home", R.drawable.ic_home),
                BottomNavigation("Meditate", R.drawable.ic_bubble),
                BottomNavigation("Sleep", R.drawable.ic_moon),
                BottomNavigation("Music", R.drawable.ic_music),
                BottomNavigation("Profile", R.drawable.ic_profile),
            ), modifier = Modifier.align(Alignment.BottomCenter)
        )

    }
}

