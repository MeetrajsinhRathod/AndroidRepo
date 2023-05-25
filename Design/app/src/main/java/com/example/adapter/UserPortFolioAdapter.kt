package com.example.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.design.R
import com.example.design.databinding.ItemPortfolioBinding
import com.example.modal.Portfolio


class UserPortFolioAdapter(
    private val portfolioList: ArrayList<Portfolio>
) : RecyclerView.Adapter<UserPortFolioAdapter.ViewHolder>() {

    inner class ViewHolder(
        private val binding: ItemPortfolioBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun setUpView(position: Int) {
            binding.portfolioImage.setImageResource(portfolioList[position].imageId)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_portfolio, parent, false)
        val binding = ItemPortfolioBinding.bind(itemView)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = portfolioList.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setUpView(position)
    }
}