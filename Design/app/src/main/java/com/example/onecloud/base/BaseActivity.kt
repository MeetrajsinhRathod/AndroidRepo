package com.example.onecloud.base

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.MotionEvent
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.design.BR
import com.example.design.R
import com.example.design.helper.hideKeyboardOnTouchOutside
import com.google.android.material.snackbar.Snackbar

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
            lifecycleOwner = this@BaseActivity
            setVariable(BR.viewModel, viewModel)
        }
        setUpView()
    }

    abstract fun setViewModel(): ViewModel?

    abstract fun getResId(): Int

    open fun setUpView() { }

    open fun showError(error: String) {
        val snack = Snackbar.make(this, binding.root, error, Snackbar.LENGTH_LONG)
        val view = snack.view
        val params = view.layoutParams as? FrameLayout.LayoutParams
        params?.gravity = Gravity.TOP
        view.layoutParams = params
        snack.setBackgroundTint(this.getColor(R.color.error_red)).show()
    }

    //override dispatchTouchEvent function to hide keyboard on touch outside of editText
    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            this.hideKeyboardOnTouchOutside()
        }
        return super.dispatchTouchEvent(event)
    }

    inline fun <reified T: AppCompatActivity> launchActivity() {
        val intent = Intent(this, T::class.java)
        startActivity(intent)
    }
}