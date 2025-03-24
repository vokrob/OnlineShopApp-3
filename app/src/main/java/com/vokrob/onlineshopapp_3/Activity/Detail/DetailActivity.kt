package com.vokrob.onlineshopapp_3.Activity.Detail

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberAsyncImagePainter
import com.vokrob.onlineshopapp_3.Activity.BaseActivity
import com.vokrob.onlineshopapp_3.Activity.Cart.CartActivity
import com.vokrob.onlineshopapp_3.Domain.ItemsModel
import com.vokrob.onlineshopapp_3.Helper.ManagmentCart
import com.vokrob.onlineshopapp_3.R

class DetailActivity : BaseActivity() {
    private lateinit var item: ItemsModel
    private lateinit var managmentCart: ManagmentCart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        item = intent.getSerializableExtra("object") as ItemsModel
        managmentCart = ManagmentCart(this)

        setContent {
            DetailScreen(
                item = item,
                onBackClick = { finish() },
                onAddToCartClick = {
                    item.numberInCart = 1
                    managmentCart.insertItems(item)
                },
                onCartClick = {
                    startActivity(
                        Intent(
                            this,
                            CartActivity::class.java
                        )
                    )
                }
            )
        }
    }

    @Composable
    private fun DetailScreen(
        item: ItemsModel,
        onBackClick: () -> Unit,
        onAddToCartClick: () -> Unit,
        onCartClick: () -> Unit
    ) {
        var selectedImageUrl by remember { mutableStateOf(item.picUrl.first()) }
        var selectedModelIndex by remember { mutableStateOf(-1) }

        Column(
            Modifier
                .fillMaxSize()
                .background(Color.White)
                .verticalScroll(rememberScrollState())
        ) {
            HeaderSection(
                selectedImageUrl = selectedImageUrl,
                imageUrls = item.picUrl,
                onBackClick = onBackClick,
            ) {
                selectedImageUrl = it
            }

            InfoSection(item)
            RatingBarRow(item.rating)

            ModelSelector(
                models = item.size,
                selectedModelIndex = selectedModelIndex,
                onModelSelected = { selectedModelIndex = it }
            )

            Text(
                text = item.description,
                fontSize = 14.sp,
                color = Color.Black,
                modifier = Modifier.padding(16.dp)
            )

            FooterSection(
                onAddToCartClick = onAddToCartClick,
                onCartClick = onCartClick
            )
        }
    }
}

@Composable
private fun HeaderSection(
    selectedImageUrl: String,
    imageUrls: List<String>,
    onBackClick: () -> Unit,
    onImageSelected: (String) -> Unit
) {
    ConstraintLayout(
        Modifier
            .fillMaxWidth()
            .height(430.dp)
            .padding(bottom = 16.dp)
    ) {
        val (back, fav, mainImage, thumbnail) = createRefs()

        Image(
            painter = rememberAsyncImagePainter(selectedImageUrl),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = colorResource(R.color.lightGrey),
                    shape = RoundedCornerShape(8.dp)
                )
                .constrainAs(mainImage) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                }
        )

        BackButton(
            onClick = onBackClick,
            modifier = Modifier.constrainAs(back) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            }
        )

        FavoriteButton(
            Modifier.constrainAs(fav) {
                top.linkTo(parent.top)
                end.linkTo(parent.end)
            }
        )

        LazyRow(
            Modifier
                .padding(vertical = 16.dp)
                .background(
                    color = colorResource(R.color.white),
                    shape = RoundedCornerShape(10.dp)
                )
                .constrainAs(thumbnail) {
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                }
        ) {
            items(imageUrls) { imageUrls ->
                ImageThumbnail(
                    imageUrl = imageUrls,
                    isSelected = selectedImageUrl == imageUrls,
                    onClick = { onImageSelected(imageUrls) }
                )
            }
        }
    }
}

@Composable
private fun BackButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(R.drawable.back),
        contentDescription = null,
        modifier = modifier
            .padding(
                start = 16.dp,
                top = 48.dp
            )
            .clickable { onClick() }
    )
}

@Composable
private fun FavoriteButton(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(R.drawable.fav_icon),
        contentDescription = null,
        modifier = modifier.padding(
            top = 48.dp,
            end = 16.dp
        )
    )
}






















