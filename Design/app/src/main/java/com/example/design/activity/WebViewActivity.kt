package com.example.design.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.design.R
import com.example.design.databinding.ActivityWebViewBinding
import java.net.URL

class WebViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWebViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpView()
    }

    private fun setUpView() {
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.webViewClient = WebViewClient()

        if(intent.data != null) {
            val url = intent.data.toString()
            binding.webView.loadUrl(url)
        } else { binding.webView.loadUrl("https://google.com") }

        binding.btnSearch.setOnClickListener {
            if (!binding.etUrl.text.isNullOrEmpty()){
                binding.webView.loadUrl(binding.etUrl.text.toString())
            }
        }
        binding.btnGoBack.setOnClickListener {
            binding.webView.goBack()
        }
        binding.btnGoForward.setOnClickListener {
            binding.webView.goForward()
        }
        binding.btnRefresh.setOnClickListener {
            binding.webView.reload()
        }
    }

    override fun onBackPressed() {
        if (binding.webView.canGoBack()) {
            binding.webView.goBack()
        } else { finish() }
    }
}