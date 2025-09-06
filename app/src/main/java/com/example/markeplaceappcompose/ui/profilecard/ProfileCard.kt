package com.example.markeplaceappcompose.ui.profilecard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun ProfileCard(navController: NavController) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp),
        elevation = CardDefaults.cardElevation(10.dp),
        shape = RoundedCornerShape(15.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable { navController.navigate("favorites") }
            ) {
                Text(fontSize = 25.sp, text = "Favorites")


            }

            HorizontalDivider(modifier = Modifier.padding(vertical = 15.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable { navController.navigate("my_listings") },
            ) {
                Text(fontSize = 25.sp, text = "My listing")

            }
            HorizontalDivider(modifier = Modifier.padding(vertical = 15.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable { navController.navigate("add_product") },
            ) {
                Text(fontSize = 25.sp, text = "Add your product")

            }


        }
    }
}
