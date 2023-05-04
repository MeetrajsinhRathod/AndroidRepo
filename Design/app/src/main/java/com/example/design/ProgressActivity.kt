package com.example.design

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import android.widget.SeekBar
import com.example.design.databinding.ActivityProgressBinding
import com.google.android.material.slider.RangeSlider
import com.google.android.material.slider.Slider

class ProgressActivity : AppCompatActivity() {
    private lateinit var binding:ActivityProgressBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProgressBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configureUI()
    }

    private fun configureUI() {
        binding.slider1.addOnChangeListener { _, value, _ ->
            binding.progressBar5.setProgress(value.toInt(),true)
        }

        binding.seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                Log.d("Seekbar", "Seekbar progress: $p1")
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {
                Log.d("Seekbar", "StartTrackingTouch")
            }
            override fun onStopTrackingTouch(p0: SeekBar?) {
                Log.d("Seekbar", "StopTrackingTouch")
            }
        })

        binding.rangeSlider.addOnChangeListener { slider, value, fromUser ->
            Log.d("rangeSlider","${slider.values}")
        }
    }
}