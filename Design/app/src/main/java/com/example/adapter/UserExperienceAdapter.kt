package com.example.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.design.R
import com.example.design.databinding.ItemUserBinding
import com.example.modal.Experience

class UserExperienceAdapter(
    private var experienceList: ArrayList<Experience>
) : RecyclerView.Adapter<UserExperienceAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun setUpView(position: Int) {
            binding.apply {
                tvCompanyRole.text = experienceList[position].role
                tvCompanyName.text = experienceList[position].companyName
                tvLocation.text = experienceList[position].location
                tvDuration.text = experienceList[position].duration
                companyImage.setImageResource(experienceList[position].companyImageId)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        val binding = ItemUserBinding.bind(itemView)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = experienceList.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setUpView(position)
    }

    fun updateData(list: ArrayList<Experience>) {
        experienceList = list
        notifyDataSetChanged()
    }
}