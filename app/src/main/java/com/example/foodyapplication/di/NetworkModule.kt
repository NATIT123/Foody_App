package com.example.foodyapplication.di

import com.example.foodyapplication.BuildConfig
import com.example.foodyapplication.base.network.AuthInterceptor
import com.example.foodyapplication.data.apis.UserAPI
import com.google.gson.GsonBuilder
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {


    @Singleton
    @Provides
    fun provideStackOverFlowApi(@Named("UserSite") retrofit: Retrofit): UserAPI {
        return retrofit.create(UserAPI::class.java)
    }


    @Provides
    @Singleton
    @Named("UserSite")
    fun provideRetrofitUserSite(
        okHttpClient: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {

        return Retrofit.Builder().addConverterFactory(gsonConverterFactory)
            .baseUrl(BuildConfig.BASE_URL_USER)
            .client(okHttpClient)
            .build()
    }


    @Provides
    @Singleton
    fun provideOKHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        authInterceptor: AuthInterceptor
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()

        builder.interceptors().add(httpLoggingInterceptor)
        builder.addInterceptor(authInterceptor)
        return builder.build()
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    @Provides
    @Singleton
    fun provideMoshiConverterFactory(): MoshiConverterFactory {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        return MoshiConverterFactory.create(moshi)
    }

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory {
        val gson = GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            .create()

        return GsonConverterFactory.create(gson)
    }


}