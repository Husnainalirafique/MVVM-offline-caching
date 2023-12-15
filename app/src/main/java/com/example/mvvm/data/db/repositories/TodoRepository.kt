package com.example.mvvm.data.db.repositories

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvm.data.db.local.TodoDao
import com.example.mvvm.data.db.remote.TodosApi
import com.example.mvvm.data.models.TodoList
import com.example.mvvm.utils.DataState
import com.example.mvvm.utils.NetworkUtils
import com.example.mvvm.utils.withIoContext
import com.example.mvvm.utils.withMainContext
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import javax.inject.Inject

class TodoRepository @Inject constructor(
    private val todoApi: TodosApi,
    private val todoDao: TodoDao,
    @ApplicationContext private val context: Context
) {
    private val _todoResponse = MutableLiveData<DataState<List<TodoList>>>()
    val todoResponse: LiveData<DataState<List<TodoList>>>
        get() = _todoResponse
    //Getting todos
    suspend fun getTodos() {
        if (NetworkUtils.isNetworkAvailable(context)) {
            withMainContext { _todoResponse.value = DataState.Loading() }
            val response = todoApi.todos()
            withMainContext {
                when {
                    response.isSuccessful && response.body() != null -> {
                        withIoContext { todoDao.insertTodo(response.body()!!) }
                        _todoResponse.value = DataState.Success(response.body()!!)
                    }

                    response.errorBody() != null -> {
                        val error = withIoContext { JSONObject(response.errorBody()!!.charStream().readText()).getString("message") }
                        _todoResponse.value = DataState.Error(error)
                    }

                    else -> {
                        _todoResponse.value = DataState.Error("Something went wrong")
                    }
                }
            }
        }
        else {
            val getTodo = todoDao.getTodo()
            withMainContext{ _todoResponse.value = DataState.Success(getTodo) }
        }
    }

}