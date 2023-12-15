package com.example.mvvm.di

import android.content.Context
import com.example.mvvm.data.db.remote.TodosApi
import com.example.mvvm.data.db.repositories.TodoRepository
import com.example.mvvm.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofitService(): Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): TodosApi = retrofit.create(TodosApi::class.java)

    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context: Context): Context {
        return context
    }
}