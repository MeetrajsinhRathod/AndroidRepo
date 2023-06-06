package com.example.design.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.design.R
import com.example.design.databinding.ItemRecyclerViewImageCellBinding
import com.example.design.helper.CardImageDataDiffUtil
import com.example.design.model.CardImageData

class CardImageAdapter(
    private val cardPosition: Int,
    private var deleteImage: (Int, Int)->Unit
): RecyclerView.Adapter<CardImageAdapter.ImageViewHolder>() {

    private var cardImageList: ArrayList<CardImageData> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_view_image_cell, parent, false)
        val binding = ItemRecyclerViewImageCellBinding.bind(itemView)
        return ImageViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return cardImageList.size
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.setUpData()
    }

    fun submitList(newCardImageDataList: ArrayList<CardImageData>) {
        val diffCallback = CardImageDataDiffUtil(cardImageList, newCardImageDataList)
        val diffCard = DiffUtil.calculateDiff(diffCallback)
        cardImageList.clear()
        cardImageList.addAll(newCardImageDataList)
        diffCard.dispatchUpdatesTo(this)
    }

    inner class ImageViewHolder(private val binding: ItemRecyclerViewImageCellBinding): RecyclerView.ViewHolder(binding.root) {

        //Function to set up views of imageCard
        fun setUpData() {
            setDeleteButtonVisibility()
            binding.apply {
                cellImageView.setImageURI(cardImageList[adapterPosition].imageId)
                btnDeleteCell.setOnClickListener { deleteImage(cardPosition, adapterPosition) }
            }

        }

        //Function to set deleteImageCard button visibility in recyclerView
        private fun setDeleteButtonVisibility() {
            binding.btnDeleteCell.visibility  = if (cardImageList.size == 1) { View.GONE } else { View.VISIBLE }
        }
    }
}