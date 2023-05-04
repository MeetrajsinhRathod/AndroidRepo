package com.example.design.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.design.R

class SpinnerItemAdapter: BaseAdapter {

    private var context:Context
    private var countryNames: Array<String>
    private var countryFlags: Array<Int>

    constructor(context: Context, countryNames: Array<String>, countryFlags: Array<Int>) : super() {
        this.context = context
        this.countryNames = countryNames
        this.countryFlags = countryFlags
    }

    override fun getCount() = countryNames.size

    override fun getItem(p0: Int): Any {
        return 0
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val view = LayoutInflater.from(context).inflate(R.layout.custom_spinner_layout, p2, false)
        val imageView = view.findViewById<ImageView>(R.id.countryImageView)
        val textView = view.findViewById<TextView>(R.id.countryTextView)
        textView.text = countryNames[p0]
        imageView.setImageResource(countryFlags[p0])
        return view
    }
}