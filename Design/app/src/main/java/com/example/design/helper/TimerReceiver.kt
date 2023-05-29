package com.example.design.helper

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class TimerReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {

        if (intent != null) {
            Toast.makeText(context, "Timer stopped after ${intent.getIntExtra("duration",-1)}s", Toast.LENGTH_LONG).show();
        }

    }
}