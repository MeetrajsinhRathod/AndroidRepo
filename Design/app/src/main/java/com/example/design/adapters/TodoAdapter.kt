package com.example.design.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.design.R
import com.example.design.databinding.ItemCountryBinding
import com.example.design.databinding.ItemTodoBinding
import com.example.design.model.Todo
import java.util.Collections

class TodoAdapter(var todoList: ArrayList<Todo>): RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    class TodoViewHolder(val binding: ItemTodoBinding): RecyclerView.ViewHolder(binding.root) {
        fun setUpData(todo: Todo) {
            binding.tbTodo.text = todo.task
        }
    }

    fun addTodo(todo: Todo){
        todoList.add(todo)
        notifyItemInserted(todoList.size-1)
    }
    fun deleteTodo(position: Int) {
        todoList.removeAt(position)
        notifyItemRemoved(position)
    }
    fun moveTodo(from:Int, to: Int) {
        Collections.swap(todoList, from, to)
        notifyItemMoved(from, to)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
        val binding = ItemTodoBinding.bind(itemView)
        return TodoViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.setUpData(todoList[position])
    }
}