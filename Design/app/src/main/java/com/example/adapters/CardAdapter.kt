package com.example.adapters

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.design.R
import com.example.design.databinding.ItemRecylerviewCellBinding
import com.example.helper.CardDataDiffUtil
import com.example.model.CardData


class CardAdapter(
    val addCard: () -> Unit,
    val deleteCard: (Int) -> Unit,
    val addImage: (Int) -> Unit,
    val deleteImage: (Int, Int) -> Unit,
    val addSpinner: (Int) -> Unit,
    val deleteSpinner: (Int, Int) -> Unit
) : RecyclerView.Adapter<CardAdapter.CardViewHolder>() {

    var cardDataList = arrayListOf<CardData>()

    fun submitList(newCardDataList: ArrayList<CardData>) {
        val diffCallback = CardDataDiffUtil(cardDataList, newCardDataList)
        val diffCard = DiffUtil.calculateDiff(diffCallback)
        cardDataList.clear()
        cardDataList.addAll(newCardDataList)
        diffCard.dispatchUpdatesTo(this)
    }

    inner class CardViewHolder(
        private val binding: ItemRecylerviewCellBinding,
        private val context: Context
    ) : RecyclerView.ViewHolder(binding.root) {

        fun setUpData() {
            setUpImageRecyclerView()
            setUpSpinnerRecyclerView()
            setAddImagesButtonVisibility()
            setDeleteButtonVisibility()
            setAddCardButtonVisibility()
            binding.cardData = cardDataList[adapterPosition]
            getTotal(adapterPosition, binding.tvTotal)

            binding.etNumber1.doOnTextChanged { text, _, _, _ ->
                if (!text.isNullOrEmpty()) {
                    cardDataList[adapterPosition].number1 = text.toString().toInt()
                    getTotal(adapterPosition, binding.tvTotal)
                }
            }

            binding.etNumber2.doOnTextChanged { text, _, _, _ ->
                if (!text.isNullOrEmpty()) {
                    cardDataList[adapterPosition].number2 = text.toString().toInt()
                    getTotal(adapterPosition, binding.tvTotal)
                }
            }

            binding.fabAddCard.setOnClickListener { addCard() }

            binding.btnDeleteCell.setOnClickListener { deleteCard(adapterPosition) }

            binding.btnAddImages.setOnClickListener { addImage(adapterPosition) }

            binding.btnAddMoreImages.setOnClickListener { addImage(adapterPosition) }

            binding.btnAddNumbers.setOnClickListener { addSpinner(adapterPosition) }
        }

        private fun setUpImageRecyclerView() {
            val imageRecyclerViewAdapter = CardImageAdapter(adapterPosition,this@CardAdapter::callDeleteImage)
            imageRecyclerViewAdapter.submitList(cardDataList[adapterPosition].imageList)
            binding.rvImages.adapter = imageRecyclerViewAdapter
            binding.rvImages.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL, false)
        }

        private fun setUpSpinnerRecyclerView() {
            val spinnerRecyclerViewAdapter = CardSpinnerAdapter(adapterPosition,this@CardAdapter::callDeleteSpinner, this@CardAdapter::getTotal, binding.tvTotal)
            spinnerRecyclerViewAdapter.submitList(cardDataList[adapterPosition].spinnerNumberList)
            binding.rvSpinnerNumbers.adapter = spinnerRecyclerViewAdapter
            binding.rvSpinnerNumbers.layoutManager = LinearLayoutManager(context)
        }

        private fun setAddImagesButtonVisibility() {
            if (cardDataList[adapterPosition].imageList.isEmpty()) {
                binding.btnAddMoreImages.visibility = View.GONE
                binding.btnAddImages.visibility = View.VISIBLE
            } else {
                binding.btnAddImages.visibility = View.GONE
                binding.btnAddMoreImages.visibility = View.VISIBLE
            }
        }

        private fun setDeleteButtonVisibility() {
            if (cardDataList.size == 1) {
                binding.btnDeleteCell.visibility = View.GONE
            } else {
                binding.btnDeleteCell.visibility = View.VISIBLE
            }
        }

        private fun setAddCardButtonVisibility() {
            if (adapterPosition == cardDataList.lastIndex) {
                binding.fabAddCard.visibility = View.VISIBLE
            } else {
                binding.fabAddCard.visibility = View.GONE
            }
        }
    }

    fun callDeleteImage(adapterPosition: Int, imagePosition: Int) {
        deleteImage(adapterPosition ,imagePosition)
    }
    fun callDeleteSpinner(adapterPosition: Int, spinnerPosition: Int) {
        deleteSpinner(adapterPosition ,spinnerPosition)
    }
    fun getTotal(position: Int, tvTotal: TextView) {
        tvTotal.text = "Total: ${(cardDataList[position].number1 * cardDataList[position].number2) + cardDataList[position].getTotalAdditionNumbers()}"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_recylerview_cell, parent, false)
        val binding = ItemRecylerviewCellBinding.bind(itemView)
        return CardViewHolder(binding, parent.context)
    }

    override fun getItemCount(): Int {
        return cardDataList.size
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.setUpData()
    }
}