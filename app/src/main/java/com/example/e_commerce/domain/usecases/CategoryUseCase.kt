package com.example.e_commerce.domain.usecases

import com.example.e_commerce.data.models.NetworkResult
import com.example.e_commerce.data.models.ProductResult
import com.example.e_commerce.data.repositories.ProductRepository
import javax.inject.Inject

class CategoryUseCase @Inject constructor(private val productRepository: ProductRepository){
    suspend fun getAllCategories() : NetworkResult<List<String>>{
        return productRepository.getAllCategories()
    }

    suspend fun getAllFromCategory(value : String) : NetworkResult<List<ProductResult>> {
        return productRepository.getAllFromCategory(value)
    }
}