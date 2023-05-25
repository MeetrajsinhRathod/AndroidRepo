package com.example.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.design.R
import com.example.design.databinding.LayoutUserProfileCellBinding
import com.example.design.databinding.LayoutUserProfileResumeCellBinding
import com.example.modal.Education
import com.example.modal.Experience
import com.example.modal.UserData
import com.example.modal.UserEnum

class UserProfileAdapter(
    private val user: UserData
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val userDataCells = UserEnum.values()
    private var singleExperienceList = arrayListOf<Experience>()
    private val singleEducationList = arrayListOf<Education>()

    inner class ExperienceViewHolder(
        private val binding: LayoutUserProfileCellBinding,
        private val context: Context
    ) : RecyclerView.ViewHolder(binding.root) {

        fun setUpView() {
            if (user.getExperienceList().isNotEmpty()) {
                singleExperienceList.add(user.getExperienceList().last())
            }
            val adapter = UserExperienceAdapter(singleExperienceList)
            var collapsed = false
            binding.apply {
                tvTitle.text = context.getString(R.string.experience)
                recyclerView.adapter = adapter
                recyclerView.layoutManager = LinearLayoutManager(context)
                btnSeeAll.setOnClickListener {
                    if (!collapsed) {
                        collapsed = true
                        adapter.updateData(user.getExperienceList())
                        btnSeeAll.text = context.getString(R.string.btn_see_less)
                    } else {
                        collapsed = false
                        adapter.updateData(singleExperienceList)
                        btnSeeAll.text = context.getString(R.string.btn_see_all)
                    }
                }
            }
        }
    }

    inner class EducationViewHolder(
        private val binding: LayoutUserProfileCellBinding,
        private val context: Context
    ) : RecyclerView.ViewHolder(binding.root) {
        fun setUpView() {
            if (user.getEducationList().isNotEmpty()) {
                singleEducationList.add(user.getEducationList().last())
            }
            val adapter = UserEducationAdapter(singleEducationList)
            var collapsed = false
            binding.apply {
                tvTitle.text = context.getString(R.string.educationCellTitle)
                recyclerView.adapter = adapter
                recyclerView.layoutManager = LinearLayoutManager(context)
                btnSeeAll.setOnClickListener {
                    if (!collapsed) {
                        collapsed = true
                        adapter.updateData(user.getEducationList())
                        btnSeeAll.text = context.getString(R.string.btn_see_less)
                    } else {
                        collapsed = false
                        adapter.updateData(singleEducationList)
                        btnSeeAll.text = context.getString(R.string.btn_see_all)
                    }
                }
            }
        }
    }

    class ResumeViewHolder(binding: LayoutUserProfileResumeCellBinding) : RecyclerView.ViewHolder(binding.root)

    inner class PortfolioViewHolder(
        private val binding: LayoutUserProfileCellBinding,
        private val context: Context
    ) : RecyclerView.ViewHolder(binding.root) {

        fun setUpView() {
            binding.apply {
                tvTitle.text = context.getString(R.string.portfolio)
                recyclerView.adapter = UserPortFolioAdapter(user.getPortfolioList())
                recyclerView.layoutManager = GridLayoutManager(context, 3)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            0 -> {
                val itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_user_profile_cell, parent, false)
                val binding = LayoutUserProfileCellBinding.bind(itemView)
                ExperienceViewHolder(binding, parent.context)
            }

            1 -> {
                val itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_user_profile_cell, parent, false)
                val binding = LayoutUserProfileCellBinding.bind(itemView)
                EducationViewHolder(binding, parent.context)
            }

            2 -> {
                val itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_user_profile_resume_cell, parent, false)
                val binding = LayoutUserProfileResumeCellBinding.bind(itemView)
                ResumeViewHolder(binding)
            }

            else -> {
                val itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_user_profile_cell, parent, false)
                val binding = LayoutUserProfileCellBinding.bind(itemView)
                PortfolioViewHolder(binding, parent.context)
            }
        }
    }

    override fun getItemCount(): Int {
        return userDataCells.count()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ExperienceViewHolder -> {
                holder.setUpView()
            }

            is EducationViewHolder -> {
                holder.setUpView()
            }

            is PortfolioViewHolder -> {
                holder.setUpView()
            }
        }
    }
}