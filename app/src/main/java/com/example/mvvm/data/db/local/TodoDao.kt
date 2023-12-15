package com.example.mvvm.data.db.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mvvm.data.models.TodoList

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todo: List<TodoList>)

    @Query("SELECT * FROM TodoList")
    fun getTodo(): List<TodoList>
}