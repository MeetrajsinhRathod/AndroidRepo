package com.example.onecloud.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import androidx.viewbinding.ViewBindings
import com.example.design.R

abstract class BaseActivity<Binding: ViewBinding, ViewModel:  BaseViewModel>(): AppCompatActivity() {

    lateinit var binding: Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)
        setUpView()
    }

    abstract fun getViewBinding(): Binding
    abstract fun setUpView()
}