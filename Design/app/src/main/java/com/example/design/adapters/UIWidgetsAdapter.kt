package com.example.design.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.design.R
import com.example.design.model.UIWidgetsEnum

class UIWidgetsAdapter(val widgetsArray: Array<UIWidgetsEnum>): RecyclerView.Adapter<UIWidgetsAdapter.ViewHolder>() {

    private lateinit var context: Context

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val btnActivity = itemView.findViewById<Button>(R.id.btnActivity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_ui_widget,parent,false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return widgetsArray.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.btnActivity.text = widgetsArray[position].name
        holder.btnActivity.setOnClickListener {
            val activityIntent = Intent(context, widgetsArray[position].cls )
            startActivity(context, activityIntent, null)
        }
    }
}