package com.example.mvvm.di

import android.content.Context
import android.provider.SyncStateContract.Constants
import androidx.room.Room
import com.example.mvvm.data.db.local.TodoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.jetbrains.annotations.Unmodifiable
import javax.annotation.Signed
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Singleton
    @Provides
    fun provideRoomDb(@ApplicationContext context: Context) = Room.databaseBuilder(
            context,
            TodoDatabase::class.java,
            "TodoDB"
        ).build()

    @Singleton
    @Provides
    fun provideDao(database: TodoDatabase) = database.todoDao()
}