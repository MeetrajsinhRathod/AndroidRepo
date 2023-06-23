package com.example.design.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.design.R
import com.example.design.adapters.UserProfileAdapter
import com.example.design.databinding.ActivityUserProfileBinding
import com.example.design.model.UserData

class UserProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserProfileBinding
    private val user = UserData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpData()
    }

    private fun setUpData() {
        binding.apply {
            reviewedLayout.tvCount.text = getString(R.string.reviewedCount)
            reviewedLayout.tvTitle.text = getString(R.string.reviewed)
            interviewLayout.tvCount.text = getString(R.string.interviewCount)
            interviewLayout.tvTitle.text = getString(R.string.interview)
            rvUserDetails.adapter = UserProfileAdapter(user)
            rvUserDetails.layoutManager = LinearLayoutManager(applicationContext)
            btnBack.setOnClickListener {
                finish()
            }
        }
    }
}