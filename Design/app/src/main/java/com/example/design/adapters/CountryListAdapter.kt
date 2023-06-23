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
import com.example.design.databinding.ItemCountryBinding

class CountryListAdapter(var countryList: ArrayList<Country>): RecyclerView.Adapter<CountryListAdapter.CountryListViewHolder>() {

    inner class CountryListViewHolder(binding: ItemCountryBinding): RecyclerView.ViewHolder(binding.root) {
        private val countryFlag: ImageView = binding.countryImageView
        private val countryName: TextView = binding.countryTextView
        private val learnMore: Button = binding.btnLearnMore
        private val countryDetails: TextView = binding.tvDetails

        fun setUpView(country: Country, position: Int) {
            countryFlag.setImageResource(countryList[position].countryFlag)
            countryName.text = countryList[position].countryName
            learnMore.visibility = View.VISIBLE
            countryDetails.visibility =
                if (countryList[position].collapsed) View.VISIBLE else View.GONE
            learnMore.text = if (countryList[position].collapsed) "See less" else "See more"

            learnMore.setOnClickListener {
                if (!countryList[position].collapsed) {
                    countryList[position].collapsed = true
                    countryDetails.visibility = View.VISIBLE
                    countryList[position].learnMoreButtonText = "See less"
                } else {
                    countryList[position].collapsed = false
                    countryDetails.visibility = View.GONE
                    countryList[position].learnMoreButtonText = "See more"
                }
                notifyItemChanged(position)
            }
        }
    }

    fun setFilteredList(list: ArrayList<Country>){
        countryList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryListViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false)
        val binding = ItemCountryBinding.bind(itemView)
        return CountryListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    override fun onBindViewHolder(holder: CountryListViewHolder, position: Int) {

        holder.setUpView(countryList[position], position)
        if (position % 2 == 0) {
            holder.itemView.setBackgroundColor(Color.LTGRAY)
        } else {
            holder.itemView.setBackgroundColor(Color.WHITE)
        }
    }
}