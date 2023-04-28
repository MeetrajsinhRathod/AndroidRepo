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
import com.google.android.material.timepicker.MaterialTimePicker

class DatePickerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_date_picker)

        val dateTV = findViewById<TextView>(R.id.dateTextView)
        val timeTV = findViewById<TextView>(R.id.timeTextView)
        val dateBtn = findViewById<Button>(R.id.selectDateBtn)
        val timeBtn = findViewById<Button>(R.id.selectTimeBtn)
        val textClock = findViewById<TextClock>(R.id.textClock)
        val calendarView = findViewById<CalendarView>(R.id.calendarView)


        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val hour = calendar.get(Calendar.HOUR)
        val minute = calendar.get(Calendar.MINUTE)


        dateBtn.setOnClickListener {

            val datePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
                val calendar1 = Calendar.getInstance()
                calendar1.set(Calendar.YEAR, year)
                calendar1.set(Calendar.MONTH, month)
                calendar1.set(Calendar.DATE, day)
                val dateFormat = DateFormat.format("EEEE, MMM d, yyyy",calendar1.time)
                dateTV.text = "Selected Date is : $dateFormat"
            }, year, month, day)
            datePickerDialog.show()
        }

        timeBtn.setOnClickListener {
            val timePickerDialog = TimePickerDialog(this,TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                timeTV.text = "Selected Time is : $hour : $minute"
            }, hour, minute, true)
            timePickerDialog.show()
        }
    }
}