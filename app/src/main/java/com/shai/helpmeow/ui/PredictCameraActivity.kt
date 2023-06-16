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

//            val savedUri = Uri.fromFile(photoFile)
//            Toast.makeText(
//                this@PredictCameraActivity,
//                "Photo Saved $savedUri",
//                Toast.LENGTH_LONG
//            ).show()





//    public override fun onResume() {
//        super.onResume()
//        hideSystemUI()
//        startCamera()
//    }
//
//    private fun buttonListener(){
//        binding.captureImage.setOnClickListener{ takePhoto() }
//        binding.switchCamera.setOnClickListener{
//            cameraSelector = if (cameraSelector == CameraSelector.DEFAULT_BACK_CAMERA) CameraSelector.DEFAULT_FRONT_CAMERA
//            else CameraSelector.DEFAULT_BACK_CAMERA
//
//            startCamera()
//        }
//    }
//
//    private fun startCamera(){
//        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)
//
//        cameraProviderFuture.addListener({
//            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
//            val preview = Preview.Builder().build().also {
//                it.setSurfaceProvider(binding.viewCamera.surfaceProvider)
//            }
//
//            imageCapture = ImageCapture.Builder().build()
//
//            try{
//                cameraProvider.unbindAll()
//                cameraProvider.bindToLifecycle(
//                    this,
//                    cameraSelector,
//                    preview,
//                    imageCapture
//                )
//            }catch (exc: Exception){
//                Toast.makeText(this, "error : ${exc.message}.", Toast.LENGTH_SHORT).show()
//            }
//        }, ContextCompat.getMainExecutor(this))
//    }
//
//    private fun takePhoto() {
//        val imageCapture = imageCapture ?: return
//        val photoFile = com.shai.helpmeow.model.createFile(application)
//        val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()
//
//        imageCapture.takePicture(
//            outputOptions,
//            ContextCompat.getMainExecutor(this),
//            object : ImageCapture.OnImageSavedCallback {
//                override fun onError(exc: ImageCaptureException) {
//                    Toast.makeText(
//                        this@PredictCameraActivity,
//                        "${exc.message}",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//                override fun onImageSaved(output: ImageCapture.OutputFileResults) {
//                    val intent = Intent(this@PredictCameraActivity, PredictActivity::class.java)
//                    intent.putExtra("picture", photoFile)
//                    intent.putExtra("isBackCamera", cameraSelector == CameraSelector.DEFAULT_BACK_CAMERA)
//                    startActivity(intent)
//
//                    val savedUri = Uri.fromFile(photoFile)
//                    Toast.makeText(
//                        this@PredictCameraActivity,
//                        "Photo Saved $savedUri",
//                        Toast.LENGTH_LONG
//                    ).show()
//                }
//            }
//        )
//    }
//
//    private fun hideSystemUI() {
//        @Suppress("DEPRECATION")
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
//            window.insetsController?.hide(WindowInsets.Type.statusBars())
//        } else {
//            window.setFlags(
//                WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN
//            )
//        }
//        supportActionBar?.hide()
//    }
}