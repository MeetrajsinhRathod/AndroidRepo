package com.example.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.design.R
import com.example.design.databinding.ItemUserBinding
import com.example.modal.Education

class UserEducationAdapter(
    private var educationList: ArrayList<Education>
) : RecyclerView.Adapter<UserEducationAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun setUpView(position: Int) {
            binding.apply {
                tvCompanyRole.text = educationList[position].degree
                tvCompanyName.text = educationList[position].collegeName
                tvLocation.text = educationList[position].location
                tvDuration.text = educationList[position].duration
                companyImage.setImageResource(educationList[position].collegeImageId)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        val binding = ItemUserBinding.bind(itemView)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = educationList.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setUpView(position)
    }

    fun updateData(list: ArrayList<Education>) {
        educationList = list
        notifyDataSetChanged()
    }
}