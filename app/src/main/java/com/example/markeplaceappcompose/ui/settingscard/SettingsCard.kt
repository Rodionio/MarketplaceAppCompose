package com.example.markeplaceappcompose.ui.settingscard

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true)
@Composable
fun SettingsCard(){
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
                    .clickable {}
            ) {
                Text(fontSize = 25.sp, text = "Change language")


            }

            HorizontalDivider(modifier = Modifier.padding(vertical = 15.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable {},
            ) {
                Text(fontSize = 25.sp, text = "Clear search history  ")

            }

            HorizontalDivider(modifier = Modifier.padding(vertical = 15.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable {}
            ) {
                Text(fontSize = 25.sp, text = "Delete account")

            }


        }
    }

}