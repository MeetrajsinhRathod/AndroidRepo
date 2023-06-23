package com.example.onecloud.modules.userProfile.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.design.R
import com.example.design.databinding.FragmentUserProfileBinding
import com.example.onecloud.modules.dashboard.activity.DashboardActivity
import com.example.onecloud.modules.userProfile.model.ProfileData
import com.example.onecloud.modules.userProfile.model.StatusData
import com.example.onecloud.modules.userProfile.viewModel.UserProfileViewModel
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class UserProfileFragment : Fragment() {

    private lateinit var binding: FragmentUserProfileBinding
    private lateinit var userToken: String
    private lateinit var userId: String
    private val viewModel by lazy {
        ViewModelProvider(this)[UserProfileViewModel::class.java]
    }
    private val sharedPref by lazy {
        activity?.getSharedPreferences(
            "application", Context.MODE_PRIVATE
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configLogOutBtn()
        binding.layoutSetStatusMsg.setOnClickListener { presentStatusMessageSheet() }
        userToken = "${sharedPref?.getString("userToken", "")}"
        userId = "${sharedPref?.getString("userId", "")}"
        binding.groupProfileComponent.visibility = View.GONE
        binding.progressBarProfile.visibility = View.VISIBLE
        getProfileAndStatusData()
        addLiveDataObservers()
    }

    private fun configLogOutBtn() {
        binding.btnLogout.setOnClickListener {
            sharedPref?.edit()?.putBoolean("isUserLoggedIn", false)?.apply()
            (activity as DashboardActivity).finish()
        }
    }

    private fun getProfileAndStatusData() {
        CoroutineScope(Dispatchers.IO).launch {
            val profileDataTask = async { viewModel.getProfileInfo() }
            val statusDataTask = async { viewModel.getStatusInfo(userId) }
            profileDataTask.await()
            statusDataTask.await()
        }
    }

    private fun addLiveDataObservers() {
        viewModel.profileSuccessResponse.observe(viewLifecycleOwner) {
            if(it!=null){
                binding.groupProfileComponent.visibility = View.VISIBLE
                binding.progressBarProfile.visibility = View.GONE
                setUserProfile(it)
            } else {
                showErrorToast("Error occurred while fetching user profile. please try again")
            }
        }
        viewModel.statusSuccessResponse.observe(viewLifecycleOwner) {
            if(it != null) {
                setStatusMessage(it)
            } else {
                showErrorToast("Error occurred while fetching status message. please try again")
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setUserProfile(profileData: ProfileData) {
        ContextCompat.getDrawable(binding.imgVwAvatar.context,R.drawable.ic_avatar)?.let {
            Picasso.get().load(profileData.avatar).placeholder(it) .into(binding.imgVwAvatar)
        }
        binding.tvUserName.text = profileData.firstName + " " + profileData.lastName
        binding.tvUserEmail.text = profileData.email
    }

    private fun setStatusMessage(statusData: StatusData) {
        binding.tvStatus.text = statusData.status
    }

    private fun showErrorToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun presentStatusMessageSheet() {
        val setStatusBottomSheetFragment = SetStatusBottomSheetFragment(userId, userToken, this::statusResponse)
        setStatusBottomSheetFragment.show(parentFragmentManager, "Status Bottom Sheet")
    }

    private fun statusResponse(statusData: StatusData?) {
        if (statusData != null) {
            binding.tvStatus.text = statusData.status
        } else {
            showErrorToast("Error occurred unable to set status message. please try again")
        }
    }
}