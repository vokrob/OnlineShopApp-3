package com.vokrob.onlineshopapp_3.Activity.Detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.vokrob.onlineshopapp_3.R

@Composable
fun ImageThumbnail(
    imageUrl: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val backColor =
        if (isSelected) colorResource(R.color.green)
        else colorResource(R.color.lightGrey)

    Box(
        Modifier
            .padding(4.dp)
            .size(50.dp)
            .then(
                if (isSelected) {
                    Modifier.border(
                        width = 1.dp,
                        color = colorResource(R.color.green),
                        shape = RoundedCornerShape(10.dp)
                    )
                } else Modifier
            )
            .clip(RoundedCornerShape(7.dp))
            .background(
                color = backColor,
                shape = RoundedCornerShape(10.dp)
            )
            .clickable(onClick = onClick)
    ) {
        Image(
            painter = rememberAsyncImagePainter(imageUrl),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}


























