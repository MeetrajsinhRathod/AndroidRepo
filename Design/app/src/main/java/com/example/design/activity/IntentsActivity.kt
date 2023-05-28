package com.example.design.activity

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.design.databinding.ActivityIntentsBinding

class IntentsActivity : AppCompatActivity() {

    private val imageCaptureCode = 1
    private val imageSelectCode = 2
    private val videoCaptureCode = 3
    private val videoSelectCode = 4
    private val dataPassActivityCode = 5

    private lateinit var binding: ActivityIntentsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntentsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSelectPhoto.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
                type = "image/*"
            }
            startActivityForResult(intent, imageSelectCode)
        }

        binding.btnCapturePhoto.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, imageCaptureCode)
        }

        binding.btnSelectVideo.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
                type = "video/*"
            }
            startActivityForResult(intent, videoSelectCode)
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
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == imageSelectCode) {
            binding.ivPhoto.setImageURI(data?.data)
            binding.ivPhoto.visibility = View.VISIBLE
        }
        if (resultCode == Activity.RESULT_OK && requestCode == imageCaptureCode && data != null) {
            val imageBitmap = data.extras?.get("data") as? Bitmap
            binding.ivPhoto.setImageBitmap(imageBitmap)
            binding.ivPhoto.visibility = View.VISIBLE
        }
        if (resultCode == Activity.RESULT_OK && requestCode == videoSelectCode) {
            binding.videoView.setVideoURI(data?.data)
            binding.videoView.visibility = View.VISIBLE
        }
        if (resultCode == Activity.RESULT_OK && requestCode == videoCaptureCode && data != null) {
            binding.videoView.setVideoURI(data?.data)
            binding.videoView.visibility = View.VISIBLE
        }
        if (resultCode == Activity.RESULT_OK && requestCode == dataPassActivityCode) {
            binding.etName.setText(data?.getStringExtra("name"))
            binding.etAge.setText(data?.getStringExtra("age"))
        }
    }
}