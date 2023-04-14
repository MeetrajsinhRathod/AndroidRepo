package com.example.adapters

import android.content.Context
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
import com.example.helper.CardSpinnerDiffUtil
import com.example.model.CardSpinnerData

class CardSpinnerAdapter(
    val cardPosition: Int,
    val deleteSpinner: (Int, Int)->Unit,
    val getTotal:(Int, TextView)-> Unit,
    val tvTotal: TextView
    ): RecyclerView.Adapter<CardSpinnerAdapter.SpinnerViewHolder>() {

    private var cardSpinnerList: ArrayList<CardSpinnerData> = arrayListOf()

    fun submitList(newCardSpinnerDataList: ArrayList<CardSpinnerData>) {
        val diffCallback = CardSpinnerDiffUtil(cardSpinnerList, newCardSpinnerDataList)
        val diffCard = DiffUtil.calculateDiff(diffCallback)
        cardSpinnerList.clear()
        cardSpinnerList.addAll(newCardSpinnerDataList)
        diffCard.dispatchUpdatesTo(this)
    }

    inner class SpinnerViewHolder(
        private val binding: ItemRecyclerViewSpinnerBinding,
        private val context:Context
        ): RecyclerView.ViewHolder(binding.root) {

        private val spinnerData = arrayListOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

        fun setUpData() {
            setSpinner(context, adapterPosition)
            setDeleteButtonVisibility()

            binding.btnDeleteSpinner.setOnClickListener { deleteSpinner(cardPosition, adapterPosition) }

            binding.spinnerNumber.onItemSelectedListener = object: OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    cardSpinnerList[adapterPosition].selectedNumber = spinnerData[p2]
                    getTotal(cardPosition, tvTotal)
                }
                override fun onNothingSelected(p0: AdapterView<*>?) { }
            }
        }

        private fun setDeleteButtonVisibility() {
            if (cardSpinnerList.size == 1) {
                binding.btnDeleteSpinner.visibility = View.GONE
            } else {
                binding.btnDeleteSpinner.visibility = View.VISIBLE
            }
        }

        private fun setSpinner(context: Context, position: Int) {
            val adapter = ArrayAdapter(context, R.layout.layout_recycler_view_spinner, spinnerData)
            adapter.setDropDownViewResource(android.R.layout.simple_list_item_1)
            binding.spinnerNumber.adapter = adapter
            binding.spinnerNumber.setSelection(cardSpinnerList[position].selectedNumber)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpinnerViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_view_spinner, parent, false)
        val binding = ItemRecyclerViewSpinnerBinding.bind(itemView)
        return SpinnerViewHolder(binding, parent.context)
    }

    override fun getItemCount(): Int {
        return cardSpinnerList.size
    }

    override fun onBindViewHolder(holder: SpinnerViewHolder, position: Int) {
        holder.setUpData()
    }
}