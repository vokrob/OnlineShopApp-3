package com.vokrob.onlineshopapp_3.Activity.Detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vokrob.onlineshopapp_3.R

@Composable
fun FooterSection(
    onAddToCartClick: () -> Unit,
    onCartClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Button(
            onClick = onAddToCartClick,
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(colorResource(R.color.green)),
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp)
                .height(50.dp)
        ) {
            Text(
                text = "Add to Cart",
                fontSize = 18.sp,
                color = Color.White
            )
        }

        IconButton(
            onClick = onCartClick,
            modifier = Modifier.background(
                color = colorResource(R.color.lightGrey),
                shape = RoundedCornerShape(10.dp)
            )
        ) {
            Icon(
                painter = painterResource(R.drawable.btn_2),
                contentDescription = "Cart",
                tint = Color.Black
            )
        }
    }
}




























