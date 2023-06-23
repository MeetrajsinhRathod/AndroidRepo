package com.example.design.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.example.design.databinding.ActivityFloatingActionButtonBinding

class FloatingActionButtonActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFloatingActionButtonBinding
    private var isFabExtended = false
    private var isCircularFabExtended = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFloatingActionButtonBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configureUI()
    }

    private fun configureUI() {
        binding.groupSendMsg.visibility = View.GONE
        binding.groupCircularFloatingButtons.visibility = View.GONE
        binding.fabSendMsg.shrink()

        binding.fabSendMsg.setOnClickListener {
            if(isFabExtended) {
                isFabExtended = false
                binding.groupSendMsg.visibility = View.GONE
                binding.fabSendMsg.shrink()
                binding.fabSendMsg.icon = ContextCompat.getDrawable(this, android.R.drawable.ic_menu_send)

            } else {
                isFabExtended = true
                binding.groupSendMsg.visibility = View.VISIBLE
                binding.fabSendMsg.icon = ContextCompat.getDrawable(this, android.R.drawable.ic_menu_close_clear_cancel)
                binding.fabSendMsg.extend()
            }
        }

        binding.fabCircular.setOnClickListener {
            if(isCircularFabExtended) {
                isCircularFabExtended = false
                binding.groupCircularFloatingButtons.visibility = View.GONE
            } else {
                isCircularFabExtended = true
                binding.groupCircularFloatingButtons.visibility = View.VISIBLE
            }
        }
    }
}