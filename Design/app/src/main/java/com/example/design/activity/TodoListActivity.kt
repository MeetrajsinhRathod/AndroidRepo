package com.example.design.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.design.adapters.TodoAdapter
import com.example.design.databinding.ActivityTodoListBinding
import com.example.design.model.Todo
import java.util.Collections

class TodoListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTodoListBinding
    private var todos = arrayListOf(Todo("task-1"), Todo("task-2"), Todo("task-3"), Todo("task-4"),
        Todo("task-5"), Todo("task-6"), Todo("task-7"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTodoListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = TodoAdapter()
        adapter.submitList(todos)

        binding.rvTodoList.adapter = adapter
        binding.rvTodoList.layoutManager = LinearLayoutManager(this)

        binding.btnAddTodo.setOnClickListener {
            if (binding.etTodoItem.text?.isEmpty() == false) {
                todos.add(Todo(binding.etTodoItem.text.toString()))
                adapter.submitList(todos)
                binding.etTodoItem.editableText.clear()
                binding.etTodoItem.onEditorAction(EditorInfo.IME_ACTION_DONE)
                binding.etTodoItem.clearFocus()
            }
        }

        val itemTouchHelper = ItemTouchHelper(object: ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN, ItemTouchHelper.LEFT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                val from = viewHolder.adapterPosition
                val to = target.adapterPosition
                Collections.swap(todos, from, to)
                adapter.submitList(todos)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
               when (direction) {
                   ItemTouchHelper.LEFT -> {
                       todos.removeAt(viewHolder.adapterPosition)
                       adapter.submitList(todos)
                   }
               }
            }
        })
        itemTouchHelper.attachToRecyclerView(binding.rvTodoList)
    }
}