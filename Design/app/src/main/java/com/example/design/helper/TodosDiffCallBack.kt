package com.example.design.helper

import android.os.Bundle
import androidx.recyclerview.widget.DiffUtil
import com.example.design.model.Todo

class TodosDiffCallBack(private val oldTodoList: ArrayList<Todo>, private val newTodoList: ArrayList<Todo>): DiffUtil.Callback() {

    override fun getOldListSize(): Int {
       return oldTodoList.size
    }

    override fun getNewListSize(): Int {
        return newTodoList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldTodoList[oldItemPosition] == newTodoList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldTodoList[oldItemPosition].task == newTodoList[newItemPosition].task
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {

        return super.getChangePayload(oldItemPosition, newItemPosition)
    }
}