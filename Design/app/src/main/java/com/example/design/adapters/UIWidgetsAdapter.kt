package com.example.design.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.AppClass
import com.example.design.R
import com.example.design.model.UIWidgetsEnum
import com.example.onecloud.Utills.SharedPrefHelper
import com.example.onecloud.modules.dashboard.activity.DashboardActivity

class UIWidgetsAdapter(private val widgetsArray: Array<UIWidgetsEnum>): RecyclerView.Adapter<UIWidgetsAdapter.ViewHolder>() {

    private lateinit var context: Context

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val btnActivity: Button = itemView.findViewById(R.id.btnActivity)
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
            val activityIntent = if (holder.btnActivity.text == UIWidgetsEnum.OneCloudLogin.name && SharedPrefHelper.isLoggedIn(context)) {
                Intent(context, DashboardActivity::class.java)
            } else {
                Intent(context, widgetsArray[position].cls)
            }
            startActivity(context, activityIntent, null)
        }
    }
}