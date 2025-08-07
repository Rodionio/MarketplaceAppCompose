package com.example.markeplaceappcompose.ui.cartcard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.markeplaceappcompose.R

@Preview(showBackground = true)
@Composable
fun CartCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        shape = RoundedCornerShape(10.dp),

        ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
        ) {
            Image(
                painter = painterResource(id = R.drawable.lamba),
                contentDescription = "image2",
                modifier = Modifier
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop

            )
            Text(fontSize = 20.sp, text = "CAR")
            Spacer(modifier = Modifier.height(8.dp))
            Text(fontSize = 20.sp, text = "10000$")
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(40.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically

            ) {
                Icon(
                    modifier = Modifier
                        .size(35.dp)
                        .clickable { },
                    imageVector = Icons.Outlined.Delete,
                    contentDescription = "delet",

                    )
                Spacer(modifier = Modifier.width(16.dp))
                Icon(
                    modifier = Modifier
                        .size(35.dp)
                        .clickable { },
                    imageVector = Icons.Outlined.FavoriteBorder,
                    contentDescription = "favorite",
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(modifier = Modifier.padding(end = 8.dp).clickable {  }, fontSize = 30.sp, text = "Buy")
            }
        }
    }
}