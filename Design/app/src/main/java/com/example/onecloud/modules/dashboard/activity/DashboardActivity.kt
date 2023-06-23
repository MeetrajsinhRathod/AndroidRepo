package com.example.onecloud.modules.dashboard.activity

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.design.R
import com.example.design.databinding.ActivityDashboardBinding
import com.example.onecloud.base.BaseActivity
import com.example.onecloud.base.BaseViewModel
import com.example.onecloud.modules.dashboard.viewModel.DashboardViewModel

class DashboardActivity : BaseActivity<ActivityDashboardBinding, BaseViewModel>() {

    private val navHostFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.bottom_nav_host_fragment_container) as? NavHostFragment
    }

    override fun setViewModel() = ViewModelProvider(this)[DashboardViewModel::class.java]

    override fun getResId(): Int = R.layout.activity_dashboard

    override fun setUpView() {
        super.setUpView()
        navHostFragment?.navController?.let {
            binding.bottomNavDashboard.setupWithNavController(
                it
            )
        }
    }
}