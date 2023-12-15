package com.example.mvvm.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TodoList(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val userId: Int,
    val title: String,
    val completed: Boolean
)