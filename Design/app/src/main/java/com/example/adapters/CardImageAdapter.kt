package com.example.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.design.R
import com.example.design.databinding.ItemRecyclerViewImageCellBinding
import com.example.helper.CardImageDataDiffUtil
import com.example.model.CardImageData

class CardImageAdapter(
    private val cardPosition: Int,
    private var deleteImage: (Int, Int)->Unit
): RecyclerView.Adapter<CardImageAdapter.ImageViewHolder>() {

    private var cardImageList: ArrayList<CardImageData> = arrayListOf()

    fun submitList(newCardImageDataList: ArrayList<CardImageData>) {
        val diffCallback = CardImageDataDiffUtil(cardImageList, newCardImageDataList)
        val diffCard = DiffUtil.calculateDiff(diffCallback)
        cardImageList.clear()
        cardImageList.addAll(newCardImageDataList)
        diffCard.dispatchUpdatesTo(this)
    }

    inner class ImageViewHolder(private val binding: ItemRecyclerViewImageCellBinding): RecyclerView.ViewHolder(binding.root) {

        fun setUpData() {
            setDeleteButtonVisibility()
            binding.cellImageView.setImageURI(cardImageList[adapterPosition].imageId)
            binding.btnDeleteCell.setOnClickListener { deleteImage(cardPosition, adapterPosition) }
        }
        private fun setDeleteButtonVisibility() {
            if (cardImageList.size == 1) {
                binding.btnDeleteCell.visibility = View.GONE
            } else {
                binding.btnDeleteCell.visibility = View.VISIBLE
            }
        }
    }

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
}