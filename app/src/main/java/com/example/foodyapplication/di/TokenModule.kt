package com.example.foodyapplication.di

import com.example.foodyapplication.common.AppSharePreference
import com.example.foodyapplication.common.TokenManager
import com.example.foodyapplication.common.TokenManagerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class TokenModule {

    @Binds
    @Singleton
    abstract fun bindTokenManager(
        impl: TokenManagerImpl
    ): TokenManager
}