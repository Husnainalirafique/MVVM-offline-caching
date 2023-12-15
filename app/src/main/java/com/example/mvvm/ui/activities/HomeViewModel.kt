package com.example.mvvm.ui.activities

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm.data.db.repositories.TodoRepository
import com.example.mvvm.data.models.TodoList
import com.example.mvvm.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val todoRepository: TodoRepository) : ViewModel() {
    val todoResponse: LiveData<DataState<List<TodoList>>>
        get() = todoRepository.todoResponse

    fun getTodos() {
        viewModelScope.launch(Dispatchers.IO){
            todoRepository.getTodos()
        }
    }
}