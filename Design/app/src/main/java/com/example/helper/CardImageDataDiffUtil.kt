package com.example.helper

import androidx.recyclerview.widget.DiffUtil
import com.example.model.CardData
import com.example.model.CardImageData

class CardImageDataDiffUtil(private val oldItemList: ArrayList<CardImageData>, private val newItemList: ArrayList<CardImageData>): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldItemList.size
    }

    override fun getNewListSize(): Int {
        return newItemList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItemPosition == newItemPosition
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItemList[oldItemPosition] == newItemList[newItemPosition]
    }
}