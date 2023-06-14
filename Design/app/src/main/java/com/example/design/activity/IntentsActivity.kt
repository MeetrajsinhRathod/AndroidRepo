package com.example.design.activity

import android.app.Activity
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.design.databinding.ActivityIntentsBinding
import com.example.design.helper.TimerReceiver

class IntentsActivity : AppCompatActivity() {

    private val dataPassActivityCode = 0
    private val timerRequestCode = 1

    private lateinit var binding: ActivityIntentsBinding

    private val imageSelectResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        binding.ivPhoto.setImageURI(it.data?.data)
        binding.ivPhoto.visibility = View.VISIBLE
    }

    private val imageCaptureResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        val imageBitmap = it.data?.extras?.get("data") as? Bitmap
        binding.ivPhoto.setImageBitmap(imageBitmap)
        binding.ivPhoto.visibility = View.VISIBLE
    }

    private val videoSelectResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        binding.videoView.setVideoURI(it.data?.data)
        binding.videoView.visibility = View.VISIBLE
        binding.videoView.start()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntentsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpView()
    }

    private fun setUpView() {

        binding.btnSelectPhoto.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
                type = "image/*"
            }
            imageSelectResult.launch(intent)
        }

        binding.btnCapturePhoto.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            imageCaptureResult.launch(intent)
        }

        binding.btnSelectVideo.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
                type = "video/*"
            }
            videoSelectResult.launch(intent)
        }

        binding.btnCaptureVideo.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
            startActivity(intent)
        }

        binding.btnSearch.setOnClickListener {
            val searchIntent = Intent(Intent.ACTION_VIEW, Uri.parse(binding.etUrl.text.toString()))
            startActivity(searchIntent)
        }

        binding.btnOpenMap.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:50.123,7.1434?z=19"))
            startActivity(intent)
        }

        binding.btnOpenDialer.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:+91 1234578900"))
            //val intent = Intent(Intent.ACTION_VIEW, Uri.parse("content://contacts/people/"))
            startActivity(intent)
        }

        binding.btnPassDataToActivity.setOnClickListener {
            val intent = Intent(this, DataPassActivity::class.java)
            intent.putExtra("name", binding.etName.text.toString())
            intent.putExtra("age", binding.etAge.text.toString())
            startActivityForResult(intent,dataPassActivityCode)
        }

        binding.btnStartTimer.setOnClickListener {
            if (!binding.etTimer.text.isNullOrEmpty()) {
                startTimer(binding.etTimer.text.toString().toInt())
            }
        }
    }

    private fun startTimer(duration: Int) {
        val timerIntent = Intent(this, TimerReceiver::class.java)
        timerIntent.putExtra("duration", duration)
        //val pendingIntent = PendingIntent.getActivity(this,timerRequestCode, timerIntent, PendingIntent.FLAG_IMMUTABLE)
        val pendingIntent = PendingIntent.getBroadcast(this, timerRequestCode, timerIntent, PendingIntent.FLAG_IMMUTABLE)
        val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (duration * 1000), pendingIntent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == dataPassActivityCode) {
            binding.etName.setText(data?.getStringExtra("name"))
            binding.etAge.setText(data?.getStringExtra("age"))
        }
    }
}