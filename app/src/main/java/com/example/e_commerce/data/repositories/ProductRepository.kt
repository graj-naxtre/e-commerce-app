package com.example.e_commerce.data.repositories

import com.example.e_commerce.data.models.NetworkResult
import com.example.e_commerce.data.models.ProductResult

interface ProductRepository {
    suspend fun getAllProducts(): NetworkResult<List<ProductResult>>
    suspend fun getSingleProduct(postId: Int): NetworkResult<ProductResult>
    suspend fun getAllCategories(): NetworkResult<List<String>>
    suspend fun getAllFromCategory(value: String): NetworkResult<List<ProductResult>>
}