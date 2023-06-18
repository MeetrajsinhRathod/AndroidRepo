package com.example.design.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.design.R
import com.example.design.databinding.ItemRecyclerViewSpinnerBinding
import com.example.design.helper.CardSpinnerDiffUtil
import com.example.design.model.CardSpinnerData

class CardSpinnerAdapter(
    val currentCardPosition: Int,
    val deleteSpinner: (Int, Int) -> Unit,
    val getTotal: (Int, TextView) -> Unit,
    val tvTotal: TextView
) : RecyclerView.Adapter<CardSpinnerAdapter.SpinnerViewHolder>() {

    private val spinnerData = (0..100).toList()
    private var cardSpinnerList: ArrayList<CardSpinnerData> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpinnerViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recycler_view_spinner, parent, false)
        val binding = ItemRecyclerViewSpinnerBinding.bind(itemView)
        return SpinnerViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return cardSpinnerList.size
    }

    override fun onBindViewHolder(holder: SpinnerViewHolder, position: Int) {
        holder.setUpData()
    }

    fun submitList(newCardSpinnerDataList: ArrayList<CardSpinnerData>) {
        val diffCallback = CardSpinnerDiffUtil(cardSpinnerList, newCardSpinnerDataList)
        val diffCard = DiffUtil.calculateDiff(diffCallback)
        cardSpinnerList.clear()
        cardSpinnerList.addAll(newCardSpinnerDataList)
        diffCard.dispatchUpdatesTo(this)
    }

    inner class SpinnerViewHolder(
        private val binding: ItemRecyclerViewSpinnerBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        //Function to set up views of spinnerCard
        fun setUpData() {
            setSpinner(adapterPosition)
            setDeleteButtonVisibility()

            binding.apply {
                btnDeleteSpinner.setOnClickListener { deleteSpinner(currentCardPosition, adapterPosition) }

                spinnerNumber.onItemSelectedListener = object : OnItemSelectedListener {

                    override fun onItemSelected(
                        adapter: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        cardSpinnerList[adapterPosition].selectedNumber = spinnerData[position]
                        getTotal(currentCardPosition, tvTotal)
                    }

                    override fun onNothingSelected(adapter: AdapterView<*>?) {}
                }
            }
        }

        //Function to set deleteSpinner button visibility in recyclerView
        private fun setDeleteButtonVisibility() {
            binding.btnDeleteSpinner.visibility = if (cardSpinnerList.size == 1) {
                View.GONE
            } else {
                View.VISIBLE
            }
        }

        /**Function to set spinner data for each item
         * @param position- current position of item in spinnerRecyclerView
         * */
        private fun setSpinner(position: Int) {
            val adapter = ArrayAdapter(binding.spinnerNumber.context, R.layout.layout_recycler_view_spinner, spinnerData)
            adapter.setDropDownViewResource(android.R.layout.simple_list_item_1)
            binding.apply {
                spinnerNumber.adapter = adapter
                spinnerNumber.setSelection(cardSpinnerList[position].selectedNumber)
            }
        }
    }
}