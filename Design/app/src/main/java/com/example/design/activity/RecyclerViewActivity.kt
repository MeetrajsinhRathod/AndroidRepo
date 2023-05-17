package com.example.design.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.design.databinding.ActivityRecyclerViewBinding

class RecyclerViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecyclerViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLinearRecyclerView.setOnClickListener {
            val linearRVPage = Intent(this, RecyclerViewLinearActivity::class.java)
            startActivity(linearRVPage)
        }
        binding.btnGridRecyclerView.setOnClickListener {
            val gridRVPage = Intent(this, RecyclerViewGridActivity::class.java)
            startActivity(gridRVPage)
        }
        binding.btnStaggeredRecyclerView.setOnClickListener {
            val staggeredRVPage = Intent(this, RecyclerViewStaggeredActivity::class.java)
            startActivity(staggeredRVPage)
        }
        binding.btnMultiViewRecyclerView.setOnClickListener {
            val multiViewRVPage = Intent(this, RecyclerViewMultipleViewHolderActivity::class.java)
            startActivity(multiViewRVPage)
        }
        binding.btnTodoList.setOnClickListener {
            val todoListPage = Intent(this, TodoListActivity::class.java)
            startActivity(todoListPage)
        }
    }
}