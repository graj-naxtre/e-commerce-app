package com.example.e_commerce.domain.usecases

import com.example.e_commerce.data.models.NetworkResult
import com.example.e_commerce.data.models.ProductResult
import com.example.e_commerce.data.repositories.ProductRepository
import javax.inject.Inject

class ProductUseCase @Inject constructor(private val productRepository: ProductRepository) {
    suspend fun getAllProducts(): NetworkResult<List<ProductResult>> {
        return productRepository.getAllProducts()
    }

    suspend fun getSingleProduct(productId: Int) : NetworkResult<ProductResult> {
        return productRepository.getSingleProduct(productId)
    }
}