package com.example.design.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.design.model.Country
import com.example.design.R
import com.example.design.databinding.CustomSpinnerLayoutBinding

class CountryListAdapter(var countryList: ArrayList<Country>): RecyclerView.Adapter<CountryListAdapter.CountryListViewHolder>() {

    class CountryListViewHolder(binding: CustomSpinnerLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        val countryFlag: ImageView = binding.countryImageView
        val countryName: TextView = binding.countryTextView
        val learnMore: Button = binding.btnLearnMore
        val countryDetails: TextView = binding.tvDetails
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryListViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.custom_spinner_layout, parent, false)
        val binding = CustomSpinnerLayoutBinding.bind(itemView)
        return CountryListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    override fun onBindViewHolder(holder: CountryListViewHolder, position: Int) {
        holder.countryFlag.setImageResource(countryList[position].countryFlag)
        holder.countryName.text = countryList[position].countryName
        holder.learnMore.visibility = View.VISIBLE
        holder.countryDetails.visibility = if (countryList[position].collapsed) View.VISIBLE else View.GONE
        holder.learnMore.text = if (countryList[position].collapsed) "See less" else "See more"

        holder.learnMore.setOnClickListener {
            if(!countryList[position].collapsed){
                countryList[position].collapsed = true
                holder.countryDetails.visibility = View.VISIBLE
                countryList[position].learnMoreButtonText = "See less"
            } else {
                countryList[position].collapsed = false
                holder.countryDetails.visibility = View.GONE
                countryList[position].learnMoreButtonText = "See more"
            }
            notifyItemChanged(position)
        }

        if (position % 2 == 0) {
            holder.itemView.setBackgroundColor(Color.LTGRAY)
        } else {
            holder.itemView.setBackgroundColor(Color.WHITE)
        }
    }
}