package com.vokrob.onlineshopapp_3.Activity.Cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberAsyncImagePainter
import com.vokrob.onlineshopapp_3.Domain.ItemsModel
import com.vokrob.onlineshopapp_3.Helper.ManagmentCart
import com.vokrob.onlineshopapp_3.R

@Composable
fun CartList(
    cartItems: ArrayList<ItemsModel>,
    managmentCart: ManagmentCart,
    onItemChange: () -> Unit
) {
    LazyColumn(Modifier.padding(top = 16.dp)) {
        items(cartItems) { item ->
            CartItem(
                cartItems = cartItems,
                item = item,
                managmentCart = managmentCart,
                onItemChange = onItemChange
            )
        }
    }
}

@Composable
fun CartItem(
    cartItems: ArrayList<ItemsModel>,
    item: ItemsModel,
    managmentCart: ManagmentCart,
    onItemChange: () -> Unit
) {
    ConstraintLayout(
        Modifier
            .fillMaxWidth()
            .padding(
                top = 8.dp,
                bottom = 8.dp
            )
    ) {
        val (pic, titleTxt, feeEachTime, totalEachItem, Quantity) = createRefs()

        Image(
            painter = rememberAsyncImagePainter(item.picUrl[0]),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(90.dp)
                .background(
                    color = colorResource(R.color.lightGrey),
                    shape = RoundedCornerShape(10.dp)
                )
                .clip(RoundedCornerShape(10.dp))
                .constrainAs(pic) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
        )

        Text(
            text = item.title,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .constrainAs(titleTxt) {
                    start.linkTo(pic.end)
                    top.linkTo(pic.top)
                }
                .padding(
                    start = 8.dp,
                    top = 8.dp
                )
        )

        Text(
            text = "$${item.price}",
            color = colorResource(R.color.green),
            modifier = Modifier
                .constrainAs(feeEachTime) {
                    start.linkTo(titleTxt.start)
                    top.linkTo(titleTxt.bottom)
                }
                .padding(
                    start = 8.dp,
                    top = 8.dp
                )
        )

        Text(
            text = "$${item.numberInCart * item.price}",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .constrainAs(totalEachItem) {
                    start.linkTo(titleTxt.start)
                    bottom.linkTo(pic.bottom)
                }
                .padding(start = 8.dp)
        )

        ConstraintLayout(
            Modifier
                .width(100.dp)
                .constrainAs(Quantity) {
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
                .background(
                    color = colorResource(R.color.lightGrey),
                    shape = RoundedCornerShape(100.dp)
                )
        ) {
            val (plusCartBtn, minusCartBtn, numberItemText) = createRefs()

            Text(
                text = item.numberInCart.toString(),
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.constrainAs(numberItemText) {
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
            )

            Box(
                Modifier
                    .padding(2.dp)
                    .size(28.dp)
                    .background(
                        color = colorResource(R.color.green),
                        shape = RoundedCornerShape(100.dp)
                    )
                    .constrainAs(minusCartBtn) {
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .clickable {
                        managmentCart.plusItem(cartItems, cartItems.indexOf(item)) {
                            onItemChange()
                        }
                    }
            ) {
                Text(
                    text = "+",
                    color = Color.White,
                    modifier = Modifier.align(Alignment.Center),
                    textAlign = TextAlign.Center
                )
            }

            Box(
                Modifier
                    .padding(2.dp)
                    .size(28.dp)
                    .background(
                        color = colorResource(R.color.white),
                        shape = RoundedCornerShape(100.dp)
                    )
                    .constrainAs(plusCartBtn) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .clickable {
                        managmentCart.minusItem(cartItems, cartItems.indexOf(item)) {
                            onItemChange()
                        }
                    }
            ) {
                Text(
                    text = "-",
                    color = Color.Green,
                    modifier = Modifier.align(Alignment.Center),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
























