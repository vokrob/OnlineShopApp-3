package com.vokrob.onlineshopapp_3.Domain

import java.io.Serializable

data class ItemModel(
    var title: String = "",
    var description: String = "",
    var categoryId: String = "",
    var sellerName: String = "",
    var sellerPic: String = "",

    var picUrl: ArrayList<String> = ArrayList(),
    var size: ArrayList<String> = ArrayList(),

    var price: Double = 0.0,
    var rating: Double = 0.0,

    var numberInCart: Int = 0,
    var sellerTell: Int = 0,

    var showRecommended: Boolean = false
) : Serializable





























