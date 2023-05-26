package com.example.design

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.design.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpData()
    }

    private fun setUpData() {
        binding.btnOneCloudDesign.setOnClickListener {
            val accountSecurityPage = Intent(this, OneCloudAccountSecurity::class.java)
            startActivity(accountSecurityPage)
        }
        binding.btnProfilePage.setOnClickListener {
            val userProfilePage = Intent(this, UserProfileActivity::class.java)
            startActivity(userProfilePage)
        }
        binding.btnMedicinePage.setOnClickListener {
            val medicinePage = Intent(this, CoughMedicineActivity::class.java)
            startActivity(medicinePage)
        }
    }
}