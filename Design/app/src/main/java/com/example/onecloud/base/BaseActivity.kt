package com.example.onecloud.base

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import androidx.viewbinding.ViewBindings
import com.example.design.BR
import com.example.design.R
import com.example.onecloud.modules.login.activity.OneCloudLoginActivity
import com.example.webService.viewModel.DeleteRequestViewModel
import com.google.android.material.snackbar.Snackbar
import java.lang.reflect.ParameterizedType

abstract class BaseActivity<Binding: ViewDataBinding, ViewModel:  BaseViewModel>(): AppCompatActivity() {

    lateinit var binding: Binding
    lateinit var viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, getResId())
        setViewModel().also {
            if (it != null) {
                viewModel = it
            }
        }
        with(binding) {
            setContentView(this.root)
            lifecycleOwner = this@BaseActivity
            setVariable(BR.viewModel, viewModel)
        }
        setUpView()
    }

    abstract fun setViewModel(): ViewModel?

    abstract fun getResId(): Int

    open fun setUpView() { }

    open fun showError(error: String) {
        Snackbar.make(
            this,
            binding.root,
            error,
            Snackbar.LENGTH_SHORT,
        ).setBackgroundTint(Color.RED).show()
    }

    inline fun <reified T: AppCompatActivity> launchActivity() {
        val intent = Intent(this, T::class.java)
        startActivity(intent)
    }
}