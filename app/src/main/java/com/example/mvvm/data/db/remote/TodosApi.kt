package com.example.mvvm.data.db.remote

import com.example.mvvm.data.models.TodoList
import retrofit2.Response
import retrofit2.http.GET

interface TodosApi {
    @GET("/todos")
    suspend fun todos(): Response<List<TodoList>>
}