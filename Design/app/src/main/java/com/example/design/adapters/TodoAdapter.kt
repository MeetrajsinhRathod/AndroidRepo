package com.example.design.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.design.R
import com.example.design.databinding.ItemCountryBinding
import com.example.design.databinding.ItemTodoBinding
import com.example.design.helper.TodosDiffCallBack
import com.example.design.model.Todo
import java.util.Collections

class TodoAdapter: RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    private val oldTodoList: ArrayList<Todo> = arrayListOf()

    inner class TodoViewHolder(val binding: ItemTodoBinding): RecyclerView.ViewHolder(binding.root) {
        fun setUpData(todo: Todo) {
            binding.tbTodo.text = todo.task
        }
    }

    fun submitList(newTodoList: ArrayList<Todo>) {
        val diffCallback = TodosDiffCallBack(oldTodoList, newTodoList)
        val diffTodos = DiffUtil.calculateDiff(diffCallback)
        oldTodoList.clear()
        oldTodoList.addAll(newTodoList)
        diffTodos.dispatchUpdatesTo(this)
    }

    fun addTodo(todo: Todo){
        oldTodoList.add(todo)
        notifyItemInserted(oldTodoList.size-1)
    }
    fun deleteTodo(position: Int) {
        oldTodoList.removeAt(position)
        notifyItemRemoved(position)
    }
    fun moveTodo(from:Int, to: Int) {
        Collections.swap(oldTodoList, from, to)
        notifyItemMoved(from, to)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
        val binding = ItemTodoBinding.bind(itemView)
        return TodoViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return oldTodoList.size
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.setUpData(oldTodoList[position])
    }
}