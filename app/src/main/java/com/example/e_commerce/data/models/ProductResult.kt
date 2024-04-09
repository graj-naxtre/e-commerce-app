package com.example.e_commerce.data.models

import com.google.gson.annotations.SerializedName

data class ProductResult(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("price")
    val price: Float,
    @SerializedName("description")
    val description: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("rating")
    val rating: Rating
)

data class Rating(@SerializedName("rate") val rate: Float, @SerializedName("count") val count: Int)
