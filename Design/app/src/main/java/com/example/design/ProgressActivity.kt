package com.example.design

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import android.widget.SeekBar
import com.google.android.material.slider.RangeSlider
import com.google.android.material.slider.Slider

class ProgressActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress)

        val progressBar = findViewById<ProgressBar>(R.id.progressBar5)
        val slider = findViewById<Slider>(R.id.slider1)
        val seekBar = findViewById<SeekBar>(R.id.seekBar)
        val rangeSlider = findViewById<RangeSlider>(R.id.rangeSlider)

        slider.addOnChangeListener { slider, value, fromUser ->
            progressBar.setProgress(value.toInt(),true)
        }
        seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
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

        rangeSlider.addOnChangeListener { slider, value, fromUser ->
            Log.d("rangeSlider","${slider.values}")
        }
    }
}