package com.example.mvvm.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.mvvm.R
import com.example.mvvm.databinding.ActivityHomeBinding
import com.example.mvvm.utils.Constants
import com.example.mvvm.utils.DataState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private val vm: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        inIt()
        vm.getTodos()
    }

    private fun inIt() {
        setOnClickListeners()
        setUpObservers()
    }

    private fun setOnClickListeners() {
    }

    private fun setUpObservers() {
        vm.todoResponse.observe(this) { dataState ->
            Log.d(Constants.TAG, "Loading stop")
            when (dataState) {
                is DataState.Success -> {
                    dataState.data?.let { todoList ->
                        todoList.forEach { todo ->
                            Log.d(Constants.TAG, todo.title)
                        }
                        binding.recyclerView.adapter = AdapterHome(todoList)
                    }

                }

                is DataState.Error -> {
                    dataState.message?.let {
                        Log.d(Constants.TAG, it)
                        Toast.makeText(this@HomeActivity, it, Toast.LENGTH_SHORT).show()
                    }
                }

                is DataState.Loading -> {
                    Log.d(Constants.TAG, "Loading.....")
                    Toast.makeText(this@HomeActivity, "Loading.....", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.unbind()
    }
}