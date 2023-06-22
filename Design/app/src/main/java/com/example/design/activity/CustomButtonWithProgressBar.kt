package com.example.design.activity

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.design.R
import com.example.design.databinding.CustomButtonWithProgressbarBinding
import com.google.android.material.progressindicator.CircularProgressIndicator

class CustomButtonWithProgressBar: LinearLayout {

    private lateinit var rootLayout: LinearLayout
    private lateinit var tvTextView: TextView
    private lateinit var pgProgressBar: CircularProgressIndicator
    var text = ""
    var textColor = 0
    var bgColor = 0
    var progressColor = 0
    var isLoaderVisible = false

    constructor(context: Context): super(context) {
        initView(context)
    }

    constructor(context: Context, attr: AttributeSet): super(context, attr) {
        getAttributesFromXml(attr, context)
        initView(context)
    }

    constructor(context: Context, attr: AttributeSet, defStyleAttr: Int): super(context, attr, defStyleAttr) {
        getAttributesFromXml(attr, context)
        initView(context)
    }

    private fun initView(context: Context) {
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        val binding = CustomButtonWithProgressbarBinding.inflate(LayoutInflater.from(context))
        val rootView = LayoutInflater.from(context).inflate(R.layout.custom_button_with_progressbar, this, true)
        rootLayout =rootView.findViewById(R.id.rootLayout)
        tvTextView = rootView.findViewById(R.id.tvTextView)
        pgProgressBar = rootView.findViewById(R.id.pgProgressBar)

        if (text.isNotEmpty()) {
            tvTextView.text = text
        }

        tvTextView.setTextColor(textColor)
        rootLayout.setBackgroundColor(bgColor)
        pgProgressBar.setIndicatorColor(progressColor)

        refreshDrawableState()
    }

    private fun getAttributesFromXml(attrs: AttributeSet?, context: Context) {
        val data = context.obtainStyledAttributes(attrs, R.styleable.CustomButtonWithProgressBar)

        text = data.getString(R.styleable.CustomButtonWithProgressBar_text).toString()
        textColor = data.getColor(R.styleable.CustomButtonWithProgressBar_textColor, Color.BLACK)
        bgColor = data.getColor(R.styleable.CustomButtonWithProgressBar_backgroundColor, Color.BLACK)
        progressColor = data.getColor(R.styleable.CustomButtonWithProgressBar_progressColor, Color.BLACK)
        isLoaderVisible = data.getBoolean(R.styleable.CustomButtonWithProgressBar_isLoading, false)

        data.recycle()
    }

    fun showLoader() {
        isLoaderVisible = false
        tvTextView.visibility = View.GONE
        pgProgressBar.visibility = View.VISIBLE
    }

    fun hideLoader() {
        isLoaderVisible = true
        tvTextView.visibility = View.VISIBLE
        pgProgressBar.visibility = View.GONE
    }

}