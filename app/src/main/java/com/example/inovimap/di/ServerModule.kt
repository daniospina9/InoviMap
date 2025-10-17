package com.example.inovimap.di

import com.example.inovimap.domain.login.usecases.GetServerResponse
import com.example.inovimap.remote.ServerApi
import com.example.inovimap.repository.LoginRepository
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServerModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl( " https://perdurably-geognostical-savannah.ngrok-free.dev")
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .build()

    @Singleton
    @Provides
    fun provideServerAPI(retrofit: Retrofit): ServerApi =
        retrofit.create(ServerApi::class.java)

    @Singleton
    @Provides
    fun provideGetServerResponse(
        loginRepository: LoginRepository
    ): GetServerResponse = GetServerResponse(
        loginRepository = loginRepository
    )
}