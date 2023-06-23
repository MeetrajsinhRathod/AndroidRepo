package com.example.design.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.design.R
import com.example.design.databinding.ItemCountryBinding
import com.example.design.model.Country

class CountryCapitalListAdapter(var countryList: ArrayList<Country>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

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

    class CapitalListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun getItemViewType(position: Int): Int {
        return if (position == 1) {
            1
        } else {
            2
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when(viewType) {
            1 -> { val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_capital, parent, false)
                CapitalListViewHolder(itemView)
            }
            else -> {
                val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false)
                val binding = ItemCountryBinding.bind(itemView)
                CountryListViewHolder(binding)
            }
        }
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is CountryListViewHolder) {
            holder.setUpView(countryList[position],position)
            if (position % 2 == 0) {
                holder.itemView.setBackgroundColor(Color.LTGRAY)
            } else {
                holder.itemView.setBackgroundColor(Color.WHITE)
            }
        }
    }
}