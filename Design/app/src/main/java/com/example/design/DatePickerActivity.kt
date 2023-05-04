package com.example.design

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.format.DateFormat
import android.widget.Button
import android.widget.CalendarView
import android.widget.DatePicker
import android.widget.TextClock
import android.widget.TextView
import com.example.design.databinding.ActivityDatePickerBinding
import com.google.android.material.timepicker.MaterialTimePicker

class DatePickerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDatePickerBinding

    private val calendar = Calendar.getInstance()
    private val year = calendar.get(Calendar.YEAR)
    private val month = calendar.get(Calendar.MONTH)
    private val day = calendar.get(Calendar.DAY_OF_MONTH)
    private val hour = calendar.get(Calendar.HOUR)
    private val minute = calendar.get(Calendar.MINUTE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDatePickerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configureUI()
    }

    private fun configureUI() {
        binding.selectDateBtn.setOnClickListener {
            val datePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { _, year, month, day ->
                val calendar1 = Calendar.getInstance()
                calendar1.set(Calendar.YEAR, year)
                calendar1.set(Calendar.MONTH, month)
                calendar1.set(Calendar.DATE, day)
                val dateFormat = DateFormat.format("EEEE, MMM d, yyyy",calendar1.time)
                binding.dateTextView.text = "Selected Date is : $dateFormat"
            }, year, month, day)
            datePickerDialog.show()
        }

        binding.selectTimeBtn.setOnClickListener {
            val timePickerDialog = TimePickerDialog(this,TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                binding.timeTextView.text = "Selected Time is : $hour : $minute"
            }, hour, minute, true)
            timePickerDialog.show()
        }
    }
}