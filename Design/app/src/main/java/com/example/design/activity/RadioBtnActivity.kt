package com.example.design.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import com.example.design.databinding.ActivityRadioBtnBinding

class RadioBtnActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRadioBtnBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRadioBtnBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configureUI()
    }

    private fun configureUI() {
        binding.radioGroup.setOnCheckedChangeListener { _, _ ->
            if(binding.radioGroup.checkedRadioButtonId != -1) {
                val checkedRadio = findViewById<RadioButton>(binding.radioGroup.checkedRadioButtonId)
                binding.responseTextView.text = "Response: ${checkedRadio.text}"
            } else {
                binding.responseTextView.text = "Response: "
            }
        }

        binding.clearBtn.setOnClickListener {
            binding.radioGroup.clearCheck()
        }
    }
}