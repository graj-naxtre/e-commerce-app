package com.example.e_commerce.domain.repositories

import com.example.e_commerce.data.database.remote.RemoteDataSource
import com.example.e_commerce.data.models.NetworkResult
import com.example.e_commerce.data.models.ProductResult
import com.example.e_commerce.data.repositories.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource) : ProductRepository {
    override suspend fun getAllProducts(): NetworkResult<List<ProductResult>> {
        return remoteDataSource.getAllProducts()
    }

    override suspend fun getSingleProduct(postId: Int): NetworkResult<ProductResult> {
        return remoteDataSource.getSingleProduct(postId)
    }

    override suspend fun getAllCategories(): NetworkResult<List<String>> {
        return  remoteDataSource.getAllCategories()
    }

    override suspend fun getAllFromCategory(value: String): NetworkResult<List<ProductResult>> {
        return remoteDataSource.getAllFromCategory(value)
    }
}