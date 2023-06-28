package com.example.design.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.Manifest
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Environment
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.example.design.databinding.ActivityPermissionModelBinding
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class PermissionModelActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPermissionModelBinding
    private lateinit var currentPhotoPath: String
    private val cameraPermission = Manifest.permission.CAMERA
    private val storagePermission = Manifest.permission.READ_EXTERNAL_STORAGE
    private val requestCameraPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                startImageCapture()
            } else {
                Toast.makeText(this, "Unable to get permission to capture an image", Toast.LENGTH_SHORT).show()
            }
        }
    private val takePictureLauncher = registerForActivityResult(ActivityResultContracts.TakePicture()) { isPictureTaken ->
            if (isPictureTaken) {
                val imageBitmap = BitmapFactory.decodeFile(currentPhotoPath)
                binding.ivPhoto.setImageBitmap(imageBitmap)
                binding.ivPhoto.visibility = View.VISIBLE
            } else {
                binding.ivPhoto.visibility = View.GONE
                Toast.makeText(this, "Image not found", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPermissionModelBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnSelectPhoto.setOnClickListener {
            if (hasCameraPermission() && hasStoragePermission()) {
                startImageCapture()
            } else {
                requestCameraPermission()
            }
        }
    }

    private fun hasCameraPermission(): Boolean {
        val result = ContextCompat.checkSelfPermission(this, cameraPermission)
        return result == PackageManager.PERMISSION_GRANTED
    }

    private fun hasStoragePermission(): Boolean {
        val result = ContextCompat.checkSelfPermission(this, storagePermission)
        return result == PackageManager.PERMISSION_GRANTED
    }

    private fun requestCameraPermission() {
        requestCameraPermissionLauncher.launch(cameraPermission)
    }

    private fun startImageCapture() {
        val photoFile: File? = try {
            createImageFile()
        } catch (ex: IOException) {
            null
        }
        photoFile?.let {
            val photoURI: Uri =
                FileProvider.getUriForFile(
                    this,
                    "${applicationContext.packageName}.fileProvider",
                    it
                )
            currentPhotoPath = it.absolutePath
            takePictureLauncher.launch(photoURI)
        }
    }

    private fun createImageFile(): File {
        val timeStamp: String =
            SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile("JPEG_${timeStamp}_", ".jpg", storageDir)
    }
}