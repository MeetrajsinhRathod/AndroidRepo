package com.example.webService.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.design.databinding.ItemUserDataBinding
import com.example.webService.model.UserData
import com.squareup.picasso.Picasso

class UserDataAdapter: RecyclerView.Adapter<UserDataAdapter.UserDataViewHolder>() {

    var userData = listOf<UserData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserDataViewHolder {
        val binding = ItemUserDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserDataViewHolder(binding)
    }

    override fun getItemCount(): Int = userData.size

    override fun onBindViewHolder(holder: UserDataViewHolder, position: Int) {
        holder.setUpView()
    }

    inner class UserDataViewHolder(val binding: ItemUserDataBinding) : RecyclerView.ViewHolder(binding.root) {

        fun setUpView() {
            binding.tvUsername.text ="${userData[adapterPosition].firstName}  ${userData[adapterPosition].lastName}"
            Picasso.get().load(userData[adapterPosition].avatar).into(binding.ivUserProfileImage)
        }
    }
}