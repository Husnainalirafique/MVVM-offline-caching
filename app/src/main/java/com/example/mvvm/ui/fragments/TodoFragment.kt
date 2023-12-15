package com.example.mvvm.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mvvm.R
import com.example.mvvm.databinding.FragmentTodoBinding
import com.example.mvvm.ui.activities.HomeViewModel

class TodoFragment :Fragment() {
    private lateinit var binding: FragmentTodoBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentTodoBinding.inflate(layoutInflater,null,false)

        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding.unbind()
    }
}