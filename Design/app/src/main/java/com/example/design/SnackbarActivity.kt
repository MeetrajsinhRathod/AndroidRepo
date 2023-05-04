package com.example.design

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import com.example.design.databinding.ActivitySnackbarBinding
import com.google.android.material.snackbar.Snackbar

class SnackbarActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySnackbarBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySnackbarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configureUI()
    }

    private fun configureUI() {
        binding.btnSimpleSnackBar.setOnClickListener {
            Snackbar.make(this,it,"This is a simple SnackBar",Snackbar.LENGTH_SHORT).show()
        }

        binding.btnSnackBarAnchor.setOnClickListener {
            Snackbar.make(this,it,"SnackBar on top of another view using setAnchorView",Snackbar.LENGTH_SHORT).setAnchorView(binding.btnSnackBarAnchor).show()
        }

        binding.btnSnackBarWithAction.setOnClickListener {
            Snackbar.make(this,it,"SnackBar with action button",Snackbar.LENGTH_SHORT).setAction("show another snackBar", object : OnClickListener {
                override fun onClick(p0: View?) {
                    Snackbar.make(this@SnackbarActivity,it,"Another SnackBar",Snackbar.LENGTH_SHORT).show()
                }
            }).show()
        }

        binding.btnIndefiniteSnackBar.setOnClickListener {
            val snackBar = Snackbar.make(this,it,"This snackbar won't dismiss itself",Snackbar.LENGTH_INDEFINITE)
            snackBar.setAction("Dismiss", object : OnClickListener {
                override fun onClick(p0: View?) {
                    snackBar.dismiss()
                }
            })
            snackBar.show()
        }

        binding.btnCustomSnackBar.setOnClickListener {
            val customSnackBar = Snackbar.make(this,it,"Custom",Snackbar.LENGTH_SHORT)
            val customView = layoutInflater.inflate(R.layout.custom_toast_layout, findViewById(R.id.customToast))
            val layout = customSnackBar.view as Snackbar.SnackbarLayout
            layout.setPadding(0,0,0,0)
            layout.addView(customView)
            customSnackBar.show()
        }
    }
}