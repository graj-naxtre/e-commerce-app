package com.example.e_commerce.data.database.remote

import com.example.e_commerce.data.models.ProductResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/products")
    suspend fun getAllProducts() : Response<List<ProductResult>>

    @GET("/products/{id}")
    suspend fun getSingleProduct(@Path("id") postId: Int) : Response<ProductResult>

    @GET("/products/categories")
    suspend fun getAllCategories() :Response<List<String>>

    @GET("/products/category/{value}")
    suspend fun getAllFromCategory(@Path("value") value: String): Response<List<ProductResult>>
}