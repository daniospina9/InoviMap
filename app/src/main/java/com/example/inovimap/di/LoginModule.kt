package com.example.inovimap.di

import com.example.inovimap.datasource.LoginDataSource
import com.example.inovimap.datasource.LoginDataSourceImpl
import com.example.inovimap.remote.ServerApi
import com.example.inovimap.repository.LoginRepository
import com.example.inovimap.repository.LoginRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LoginModule {

    @Singleton
    @Provides
    fun provideLoginDatasource(
        api: ServerApi
    ): LoginDataSource = LoginDataSourceImpl(
        api = api
    )

    @Singleton
    @Provides
    fun provideLoginRepository(
        loginDataSource: LoginDataSource
    ): LoginRepository = LoginRepositoryImpl(
        loginDataSource = loginDataSource
    )
}