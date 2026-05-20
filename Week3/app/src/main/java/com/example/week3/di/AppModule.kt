package com.example.week3.di

import android.content.Context
import com.example.week3.DataStoreManager
import com.example.week3.RetrofitClient
import com.example.week3.UserService
import com.example.week3.data.LocalRepository
import com.example.week3.data.RemoteRepository
import com.example.week3.data.ShoppingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // ✅ Retrofit API
    @Provides
    @Singleton
    fun provideUserService(): UserService =
        RetrofitClient.instance

    // ✅ DataStore
    @Provides
    @Singleton
    fun provideDataStoreManager(
        @ApplicationContext context: Context
    ): DataStoreManager =
        DataStoreManager(context)

    // ✅ LocalRepository
    @Provides
    @Singleton
    fun provideLocalRepository(
        dataStoreManager: DataStoreManager
    ): LocalRepository =
        LocalRepository(dataStoreManager)

    // ✅ RemoteRepository
    @Provides
    @Singleton
    fun provideRemoteRepository(
        userService: UserService
    ): RemoteRepository =
        RemoteRepository(userService)

    // ✅ ⭐ 핵심: ShoppingRepository (통합 Repository)
    @Provides
    @Singleton
    fun provideShoppingRepository(
        localRepository: LocalRepository,
        remoteRepository: RemoteRepository
    ): ShoppingRepository =
        ShoppingRepository(localRepository, remoteRepository)
}