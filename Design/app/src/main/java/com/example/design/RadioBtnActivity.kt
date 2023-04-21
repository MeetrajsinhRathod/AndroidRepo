package com.example.design

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView

class RadioBtnActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_radio_btn)

        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val respose = findViewById<TextView>(R.id.responseTextView)
        val clearBtn = findViewById<Button>(R.id.clearBtn)

        radioGroup.setOnCheckedChangeListener { radioGroup, i ->
            if(radioGroup.checkedRadioButtonId != -1) {
                val checkedRadio = findViewById<RadioButton>(radioGroup.checkedRadioButtonId)
                respose.setText("Response: ${checkedRadio.text}")
            } else {
                respose.setText("Response: ")
            }
        }

        clearBtn.setOnClickListener {
            radioGroup.clearCheck()
        }

    }
}