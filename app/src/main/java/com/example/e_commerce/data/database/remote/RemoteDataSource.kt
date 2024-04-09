package com.example.e_commerce.data.database.remote

import com.example.e_commerce.data.models.NetworkResult
import com.example.e_commerce.data.models.ProductResult
import com.example.e_commerce.data.utils.handleApi
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService) {
    suspend fun getAllProducts(): NetworkResult<List<ProductResult>> {
        return handleApi { apiService.getAllProducts() }
    }

    suspend fun getSingleProduct(postId: Int): NetworkResult<ProductResult> {
        return handleApi { apiService.getSingleProduct(postId) }
    }

    suspend fun getAllCategories(): NetworkResult<List<String>> {
        return handleApi { apiService.getAllCategories() }
    }

    suspend fun getAllFromCategory(value: String): NetworkResult<List<ProductResult>> {
        return handleApi { apiService.getAllFromCategory(value) }
    }
}