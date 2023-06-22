package com.example.design.helper

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class TimerReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == Intent.ACTION_AIRPLANE_MODE_CHANGED) {
            Toast.makeText(context, "Airplane mode changed", Toast.LENGTH_LONG).show();
        }
        if (intent != null) {
            Toast.makeText(context, "Timer stopped after ${intent.getIntExtra("duration",-1)}s", Toast.LENGTH_LONG).show();
        }
    }
}