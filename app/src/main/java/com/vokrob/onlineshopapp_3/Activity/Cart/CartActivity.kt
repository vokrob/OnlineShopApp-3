package com.vokrob.onlineshopapp_3.Activity.Cart

import android.os.Bundle
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.vokrob.onlineshopapp_3.Activity.BaseActivity
import com.vokrob.onlineshopapp_3.Helper.ManagmentCart
import com.vokrob.onlineshopapp_3.R

class CartActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge(
            SystemBarStyle.light(
                darkScrim = Color.White.toArgb(),
                scrim = Color.White.toArgb()
            )
        )

        setContent {
            CartScreen(
                managmentCart = ManagmentCart(this),
                onBackClick = { finish() }
            )
        }
    }
}

@Composable
fun CartScreen(
    managmentCart: ManagmentCart = ManagmentCart(LocalContext.current),
    onBackClick: () -> Unit
) {
    var cartItem = remember { mutableStateOf(managmentCart.getListCart()) }
    val tax = remember { mutableStateOf(0.0) }

    calculatorCart(managmentCart, tax)

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        ConstraintLayout(Modifier.padding(top = 36.dp)) {
            val (backBtn, cartTxt) = createRefs()

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(cartTxt) { centerTo(parent) },
                text = "Your Cart",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp
            )

            Image(
                painter = painterResource(R.drawable.back),
                contentDescription = null,
                modifier = Modifier
                    .constrainAs(backBtn) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                    }
                    .clickable { onBackClick() }
            )
        }

        if (cartItem.value.isEmpty()) {
            Text(
                text = "Cart Is Empty",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        } else {
            CartList(
                cartItems = cartItem.value,
                managmentCart = managmentCart
            ) {
                cartItem.value = managmentCart.getListCart()

                calculatorCart(
                    managmentCart = managmentCart,
                    tax = tax
                )
            }

            CartSummary(
                itemTotal = managmentCart.getTotalFee(),
                tax = tax.value,
                delivery = 10.0
            )
        }
    }
}

fun calculatorCart(
    managmentCart: ManagmentCart,
    tax: MutableState<Double>
) {
    val percentTax = 0.02
    tax.value = Math.round((managmentCart.getTotalFee() * percentTax) * 100) / 100.0
}




















