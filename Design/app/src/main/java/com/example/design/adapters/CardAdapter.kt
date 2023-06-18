package com.example.design.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.design.R
import com.example.design.activity.CardDataSetupInterface
import com.example.design.databinding.ItemRecylerviewCellBinding
import com.example.design.helper.CardDataDiffUtil
import com.example.design.model.CardData

class CardAdapter(
    val cardDataSetUp: CardDataSetupInterface
) : RecyclerView.Adapter<CardAdapter.CardViewHolder>() {

    private var cardDataList = arrayListOf<CardData>()
    private lateinit var imageRecyclerViewAdapter: CardImageAdapter
    private lateinit var spinnerRecyclerViewAdapter: CardSpinnerAdapter

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recylerview_cell, parent, false)
        val binding = ItemRecylerviewCellBinding.bind(itemView)
        return CardViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return cardDataList.size
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.setUpData()
    }

    fun submitList(newCardDataList: ArrayList<CardData>) {
        val diffCallback = CardDataDiffUtil(cardDataList, newCardDataList)
        val diffCard = DiffUtil.calculateDiff(diffCallback)
        cardDataList.clear()
        cardDataList.addAll(newCardDataList)
        diffCard.dispatchUpdatesTo(this)
    }

    fun getTotal(position: Int, tvTotal: TextView) {
        tvTotal.text = "Total: ${cardDataList[position].getResult()}"
    }

    inner class CardViewHolder(
        private val binding: ItemRecylerviewCellBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        //Function to set up views of card
        fun setUpData() {
            setUpImageRecyclerView()
            setUpSpinnerRecyclerView()
            setAddImagesButtonVisibility()
            setDeleteButtonVisibility()
            setAddCardButtonVisibility()
            getTotal(adapterPosition, binding.tvTotal)

            binding.apply {
                cardData = cardDataList[adapterPosition]
                etNumber1.doOnTextChanged { text, _, _, _ ->
                    if (!text.isNullOrEmpty()) {
                        cardDataList[adapterPosition].multiplicationNumber1 =
                            text.toString().toInt()
                    } else {
                        cardDataList[adapterPosition].multiplicationNumber1 = 0
                    }
                    getTotal(adapterPosition, binding.tvTotal)
                }

                etNumber2.doOnTextChanged { text, _, _, _ ->
                    if (!text.isNullOrEmpty()) {
                        cardDataList[adapterPosition].multiplicationNumber2 =
                            text.toString().toInt()
                    } else {
                        cardDataList[adapterPosition].multiplicationNumber2 = 0
                    }
                    getTotal(adapterPosition, binding.tvTotal)
                }

                fabAddCard.setOnClickListener { cardDataSetUp.addCard() }

                btnDeleteCell.setOnClickListener { cardDataSetUp.deleteCard(adapterPosition) }

                btnAddImages.setOnClickListener { cardDataSetUp.pickImage(adapterPosition) }

                btnAddMoreImages.setOnClickListener { cardDataSetUp.pickImage(adapterPosition) }

                btnAddNumbers.setOnClickListener { cardDataSetUp.addSpinner(adapterPosition) }
            }
        }

        //Function to set image recyclerView inside a card
        private fun setUpImageRecyclerView() {
            imageRecyclerViewAdapter = CardImageAdapter(adapterPosition, cardDataSetUp::deleteImage)
            imageRecyclerViewAdapter.submitList(cardDataList[adapterPosition].imageList)
            binding.rvImages.apply {
                adapter = imageRecyclerViewAdapter
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            }
        }

        //Function to set spinner recyclerView inside a card
        private fun setUpSpinnerRecyclerView() {
            spinnerRecyclerViewAdapter = CardSpinnerAdapter(
                adapterPosition,
                cardDataSetUp::deleteSpinner,
                this@CardAdapter::getTotal,
                binding.tvTotal
            )
            spinnerRecyclerViewAdapter.submitList(cardDataList[adapterPosition].spinnerNumberList)
            binding.rvSpinnerNumbers.apply {
                adapter = spinnerRecyclerViewAdapter
                layoutManager = LinearLayoutManager(context)
            }
        }

        //Function to set addImageButton visibility according to number of images in list
        private fun setAddImagesButtonVisibility() {
            if (cardDataList[adapterPosition].imageList.isEmpty()) {
                binding.btnAddMoreImages.visibility = View.GONE
                binding.btnAddImages.visibility = View.VISIBLE
            } else {
                binding.btnAddImages.visibility = View.GONE
                binding.btnAddMoreImages.visibility = View.VISIBLE
            }
        }

        //Function to set deleteCard button visibility according to number of cards in recyclerView
        private fun setDeleteButtonVisibility() {
            if (cardDataList.size == 1) {
                binding.btnDeleteCell.visibility = View.GONE
            } else {
                binding.btnDeleteCell.visibility = View.VISIBLE
            }
        }

        //Function to set addCard button visibility according to number of cards in recyclerView
        private fun setAddCardButtonVisibility() {
            if (adapterPosition == cardDataList.lastIndex) {
                binding.fabAddCard.visibility = View.VISIBLE
            } else {
                binding.fabAddCard.visibility = View.GONE
            }
        }
    }
}