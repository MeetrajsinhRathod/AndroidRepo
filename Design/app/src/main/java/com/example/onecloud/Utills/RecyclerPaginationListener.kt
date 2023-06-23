package com.example.onecloud.Utills

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecyclerPaginationListener(
    private val itemRemainCount: Int = 1,
    private val onPageChange: () -> Unit
) : RecyclerView.OnScrollListener() {
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
//        val adapter = recyclerView.adapter as? BaseRecyclerAdapter<*>
//        adapter?.let {
//            if (!adapter.isLoading()) {
//                val linearLayoutManager: LinearLayoutManager? =
//                    recyclerView.layoutManager as? LinearLayoutManager
//                linearLayoutManager?.let {
//                    if (it.findLastCompletelyVisibleItemPosition() >= it.itemCount - itemRemainCount) {
//                        onPageChange.invoke()
//                    }
//                }
//            }
//        }
    }
}