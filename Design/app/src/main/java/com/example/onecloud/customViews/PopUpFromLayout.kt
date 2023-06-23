package com.example.onecloud.customViews

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import android.widget.PopupWindow
import android.widget.TextView
import androidx.core.widget.PopupWindowCompat

class PopUpFromLayout(
    private val context: Context,
    layout: Int,
    private val anchor: View, private val xOff: Int,
    private val yOff: Int
) {
    private val popUpView by lazy { LayoutInflater.from(context).inflate(layout, null) }
    private val popupWindow by lazy {
        PopupWindow(
            popUpView, LayoutParams.WRAP_CONTENT,
            LayoutParams.WRAP_CONTENT, true
        )
    }

    fun showPopup() {
        popupWindow.elevation = 10f
        PopupWindowCompat.showAsDropDown(popupWindow, anchor, xOff, yOff, Gravity.START)
    }

    fun onItemSelectedListener(action: (id: Int) -> Unit) {
        if (popUpView as? ViewGroup != null) {
            for (viewPos in 0..(popUpView as ViewGroup).childCount) {
                val currentView = (popUpView as ViewGroup).getChildAt(viewPos)
                if (currentView is TextView) {
                    currentView.setOnClickListener {
                        popupWindow.dismiss()
                        action(currentView.id)
                    }
                }
            }
        }
    }
}
