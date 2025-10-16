package com.example.inovimap.di

import com.example.inovimap.domain.login.usecases.ValidateEmail
import com.example.inovimap.repository.LoginRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LoginUseCaseModule {

    @Singleton
    @Provides
    fun provideValidateEmail(
        loginRepository: LoginRepository
    ): ValidateEmail = ValidateEmail(
        loginRepository = loginRepository
    )
}