package com.shai.helpmeow.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.shai.helpmeow.R
import com.shai.helpmeow.databinding.ActivityPredictCameraBinding
import com.shai.helpmeow.model.createCustomTempFile
import java.io.File


class PredictCameraActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPredictCameraBinding

    private var file: File? = null
    private lateinit var photoPath: String
    private var imageCapture: ImageCapture? = null
    private var cameraSelector: CameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

//    private val CAMERA_PERMISSION_REQUEST_CODE = 1

    companion object {
        private val PERMISSION = arrayOf(Manifest.permission.CAMERA)
        private const val PERMISSION_CODE = 10
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_CODE) {
            if (!allPermissionsGranted()) {
                Toast.makeText(
                    this,
                    getString(R.string.permission),
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            }
        }
    }

    private fun allPermissionsGranted() = PERMISSION.all {
        ContextCompat.checkSelfPermission(baseContext, it) == PackageManager.PERMISSION_GRANTED
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPredictCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

//        buttonListener()
//
//        binding.btnBack.setOnClickListener{
//            finish()
//        }
        if (!allPermissionsGranted()) {
            ActivityCompat.requestPermissions(
                this,
                PostActivity.PERMISSION,
                PostActivity.PERMISSION_CODE
            )

        }
        openCamera()
    }

    private fun openCamera() {
        createCustomTempFile(application).also { file ->
            val photoUri: Uri = FileProvider.getUriForFile(
                this@PredictCameraActivity,
                "com.shai.helpmeow",
                file
            )
            photoPath = file.absolutePath

            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE).apply {
                putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
            }

            intent.resolveActivity(packageManager)?.let {
                cameraLauncher.launch(intent)
            }
        }
    }

    private val cameraLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        cameraResult(result)
    }

    private fun cameraResult(result: ActivityResult) {
        if (result.resultCode == RESULT_OK) {
            val photoFile = File(photoPath)
            file = photoFile

            val bitmapResult = BitmapFactory.decodeFile(file?.path)
            binding.viewCamera.setImageBitmap(bitmapResult)

            val intent = Intent(this@PredictCameraActivity, PredictActivity::class.java)
            intent.putExtra("picture", photoFile)
            startActivity(intent)
        } else {
            Toast.makeText(this, "Failed to capture image", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
