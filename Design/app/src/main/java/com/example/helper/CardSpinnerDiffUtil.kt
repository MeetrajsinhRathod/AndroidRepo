package com.example.helper

import androidx.recyclerview.widget.DiffUtil
import com.example.model.CardImageData
import com.example.model.CardSpinnerData

class CardSpinnerDiffUtil(private val oldItemList: ArrayList<CardSpinnerData>, private val newItemList: ArrayList<CardSpinnerData>): DiffUtil.Callback() {
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