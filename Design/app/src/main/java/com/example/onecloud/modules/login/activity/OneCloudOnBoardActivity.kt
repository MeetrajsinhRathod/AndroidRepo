package com.example.onecloud.modules.login.activity

import android.content.Intent
import com.example.design.databinding.ActivityOneCloudOnboardBinding
import com.example.onecloud.base.BaseActivity
import com.example.onecloud.base.BaseViewModel

class OneCloudOnBoardActivity : BaseActivity<ActivityOneCloudOnboardBinding, BaseViewModel>() {

    override fun getViewBinding(): ActivityOneCloudOnboardBinding = ActivityOneCloudOnboardBinding.inflate(layoutInflater)

    override fun setUpView() {
        binding.btnLogin.setOnClickListener {
            val intent = Intent(this,OneCloudLoginActivity::class.java)
            startActivity(intent)
        }
    }
}