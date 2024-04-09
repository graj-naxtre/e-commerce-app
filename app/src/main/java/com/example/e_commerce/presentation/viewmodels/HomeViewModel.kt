package com.example.e_commerce.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerce.data.models.NetworkResult
import com.example.e_commerce.domain.usecases.CategoryUseCase
import com.example.e_commerce.domain.usecases.ProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productUseCase: ProductUseCase,
    private val categoryUseCase: CategoryUseCase
) : ViewModel() {
    private val _productState = MutableStateFlow<HomeState>(HomeState.START)
    val productState = _productState.asStateFlow()

    private val _categoryState = MutableStateFlow<HomeState>(HomeState.START)
    val categoryState = _categoryState.asStateFlow()

    private val _categoryProductState = MutableStateFlow<HomeState>(HomeState.START)
    val categoryProductState = _categoryProductState.asStateFlow()

    private val _singleProductState = MutableStateFlow<HomeState>(HomeState.START)
    val singleProductState = _singleProductState.asStateFlow()

    fun getAllProducts() {
        viewModelScope.launch {
            _productState.emit(HomeState.LOADING)
            when (val response = productUseCase.getAllProducts()) {
                is NetworkResult.Success -> {
                    val productList = response.data
                    _productState.emit(HomeState.SUCCESS(data = productList))
                }
                is NetworkResult.Error -> {
                    _productState.emit(HomeState.FAILURE(message = "${response.message} code:${response.code}"))
                }
                is NetworkResult.Exception -> {
                    _productState.emit(HomeState.FAILURE(message = "${response.e.message} reason:${response.e}"))
                }
            }
        }
    }

    fun getAllCategories() {
        viewModelScope.launch {
            _categoryState.emit(HomeState.LOADING)
            when (val response = categoryUseCase.getAllCategories()) {
                is NetworkResult.Success -> {
                    val categoryList = response.data
                    _categoryState.emit(HomeState.SUCCESS(data = categoryList))
                }
                is NetworkResult.Error -> {
                    _categoryState.emit(HomeState.FAILURE(message = "${response.message} code:${response.code}"))
                }
                is NetworkResult.Exception -> {
                    _categoryState.emit(HomeState.FAILURE(message = "${response.e.message} reason:${response.e}"))
                }
            }
        }
    }

    fun getSingleProduct(productId: Int) {
        viewModelScope.launch {
            _singleProductState.emit(HomeState.LOADING)
            when(val response = productUseCase.getSingleProduct(productId)){
                is NetworkResult.Success -> {
                    val singleProduct = response.data
                    _singleProductState.emit(HomeState.SUCCESS(data = singleProduct))
                }
                is NetworkResult.Error -> {
                    _singleProductState.emit(HomeState.FAILURE(message = "${response.message} code:${response.code}"))
                }
                is NetworkResult.Exception -> {
                    _singleProductState.emit(HomeState.FAILURE(message = "${response.e.message} reason:${response.e}"))
                }
            }
        }
    }

    fun getAllFromCategory(categoryName: String) {
        viewModelScope.launch {
            _categoryProductState.emit(HomeState.LOADING)
            if(categoryName == "All"){
                when (val response = productUseCase.getAllProducts()) {
                    is NetworkResult.Success -> {
                        val categoryProductList = response.data
                        _categoryProductState.emit(HomeState.SUCCESS(data = categoryProductList))
                    }
                    is NetworkResult.Error -> {
                        _categoryProductState.emit(HomeState.FAILURE(message = "${response.message} code:${response.code}"))
                    }
                    is NetworkResult.Exception -> {
                        _categoryProductState.emit(HomeState.FAILURE(message = "${response.e.message} reason:${response.e}"))
                    }
                }

            } else {
                when (val response = categoryUseCase.getAllFromCategory(categoryName)) {
                    is NetworkResult.Success -> {
                        val categoryProductList = response.data
                        _categoryProductState.emit(HomeState.SUCCESS(data = categoryProductList))
                    }
                    is NetworkResult.Error -> {
                        _categoryProductState.emit(HomeState.FAILURE(message = "${response.message} code:${response.code}"))
                    }
                    is NetworkResult.Exception -> {
                        _categoryProductState.emit(HomeState.FAILURE(message = "${response.e.message} reason:${response.e}"))
                    }
                }
            }
        }
    }
}

sealed class HomeState {
    data object START : HomeState()
    data object LOADING : HomeState()
    data class SUCCESS<T>(val data: T) : HomeState()
    data class FAILURE(val message: String) : HomeState()
}