package com.example.mvvm.data.db.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mvvm.data.models.TodoList

@Database(entities = [TodoList::class], version = 1, exportSchema = false)
abstract class TodoDatabase :RoomDatabase(){
    abstract fun todoDao():TodoDao
}