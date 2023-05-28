package com.example.design.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.design.R
import com.example.design.databinding.ActivityDataBindingBinding
import com.example.design.databinding.ActivityDataPassBinding

class DataPassActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDataPassBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDataPassBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intentFromActivity = intent
        binding.etName.setText(intentFromActivity.getStringExtra("name"))
        binding.etAge.setText(intentFromActivity.getStringExtra("age"))

        binding.btnSendData.setOnClickListener {
            finish()
        }
    }

    override fun finish() {
        val intent = Intent()
        intent.putExtra("name", binding.etName.text.toString())
        intent.putExtra("age", binding.etAge.text.toString())
        setResult(RESULT_OK,intent)
        super.finish()
    }
}