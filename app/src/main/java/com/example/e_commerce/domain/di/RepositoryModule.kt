package com.example.e_commerce.domain.di

import com.example.e_commerce.data.repositories.ProductRepository
import com.example.e_commerce.domain.repositories.ProductRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideProductRepository(impl: ProductRepositoryImpl): ProductRepository
}