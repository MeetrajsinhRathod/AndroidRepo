package com.example.design.helper

import androidx.recyclerview.widget.DiffUtil
import com.example.design.model.CardData

class CardDataDiffUtil(private val oldItemList: ArrayList<CardData>, private val newItemList: ArrayList<CardData>): DiffUtil.Callback() {
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