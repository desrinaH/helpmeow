package com.shai.helpmeow.ui

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.shai.helpmeow.R
import com.shai.helpmeow.databinding.ActivityPostBinding
import com.shai.helpmeow.model.*
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class PostActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPostBinding
    private lateinit var photoPath: String
    private lateinit var viewModel: PostViewModel
    private lateinit var sharedPreferences: SharedPreferences
    private val dataStore: DataStore<Preferences> by preferencesDataStore(name = "session")

    private lateinit var adapterRole: ArrayAdapter<String>
    private lateinit var adapterBreed: ArrayAdapter<String>
    private lateinit var adapterGender: ArrayAdapter<String>
    private lateinit var adapterCity: ArrayAdapter<String>

    private val MAP_REQUEST_CODE = 123

    private var file: File? = null
    private var selectedLatitude: Double = 0.0
    private var selectedLongitude: Double = 0.0

    companion object {
        internal val PERMISSION = arrayOf(Manifest.permission.CAMERA)
        internal const val PERMISSION_CODE = 10
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
        binding = ActivityPostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val actionBar: ActionBar? = supportActionBar
        val color = ContextCompat.getColor(this, R.color.light_orange)
        actionBar?.setBackgroundDrawable(ColorDrawable(color))

        adapterRole = ArrayAdapter(this, R.layout.item_list, Object.role)
        adapterBreed = ArrayAdapter(this, R.layout.item_list, Object.breed)
        adapterGender = ArrayAdapter(this, R.layout.item_list, Object.gender)
        adapterCity = ArrayAdapter(this, R.layout.item_list, Object.city)

        binding.roleEditText.setAdapter(adapterRole)
        binding.breedEditText.setAdapter(adapterBreed)
        binding.genderEditText.setAdapter(adapterGender)
        binding.cityEditText.setAdapter(adapterCity)



        if (!allPermissionsGranted()) {
            ActivityCompat.requestPermissions(
                this,
                PERMISSION,
                PERMISSION_CODE
            )
        }

        val addLocation: LinearLayout = findViewById(R.id.addLocation)
        addLocation.setOnClickListener {
            val intent = Intent(this, MapsActivity::class.java)
            startActivityForResult(intent, MAP_REQUEST_CODE)
        }

        showLoad(false)
        setupView()

        sharedPreferences = getSharedPreferences("MyApp", MODE_PRIVATE)

//        val userId = getUserId()

        val userId = sharedPreferences.getString("userId", "") ?: ""
        binding.apply {
            cameraButton.setOnClickListener { openCamera() }
            galleryButton.setOnClickListener { openGallery() }
            uploadButton.setOnClickListener { uploadImage(userId) }
        }
    }

    @Deprecated("Deprecated in Java")
    @SuppressLint("SetTextI18n")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == MAP_REQUEST_CODE && resultCode == RESULT_OK) {
            selectedLatitude = data?.getDoubleExtra("latitude", 0.0) ?: 0.0
            selectedLongitude = data?.getDoubleExtra("longitude", 0.0) ?: 0.0
            binding.latitudeTextView.text = "Latitude: $selectedLatitude"
            binding.longitudeTextView.text = "Longitude: $selectedLongitude"
        }
    }


    private fun getUserId(): String {
        return sharedPreferences.getString("userId", "") ?: ""
    }

    private fun setupView() {
        val pref = UserPreference.getInstance(dataStore)
        viewModel = ViewModelProvider(this, ViewModelFactory(pref, this))[PostViewModel::class.java]

        viewModel.story.observe(this) { data ->
            when (data) {
                is DataProgress.Success -> handleSuccess(data.resultData)
                is DataProgress.Loading -> handleLoading()
                is DataProgress.Error -> handleError(data.errorMessage)
            }
        }
    }

    private fun handleSuccess(resultData: String?) {
        Toast.makeText(this, resultData, Toast.LENGTH_SHORT).show()
        finish()
        showLoad(false)
    }

    private fun handleLoading() {
        showLoad(true)
    }

    private fun handleError(errorMessage: String?) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        showLoad(false)
    }

    private fun uploadImage(userId: String) {
        if (userId.isNotEmpty()) {
            file?.let { file ->
                val reducedFile = reduceFileImage(file)

                val requestImageFile = reducedFile.asRequestBody("image/jpeg".toMediaTypeOrNull())
                val imageMultipart: MultipartBody.Part = MultipartBody.Part.createFormData(
                    "file",
                    reducedFile.name,
                    requestImageFile
                )

                lifecycleScope.launch {
                    viewModel.setStory(userId, imageMultipart)
                }
            } ?: run {
                Toast.makeText(
                    this@PostActivity,
                    getString(R.string.input_image),
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        }
    }

    private fun openGallery() {
        val intent = Intent().apply {
            action = Intent.ACTION_GET_CONTENT
            type = "image/*"
        }
        val chooser = Intent.createChooser(intent, "Choose a Picture")
        galleryLauncher.launch(chooser)
    }

    private val galleryLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        galleryResult(result)
    }

    private fun galleryResult(result: ActivityResult) {
        if (result.resultCode == RESULT_OK) {
            val selectedPhoto: Uri = result.data?.data as Uri
            val photoFile = uriToFile(selectedPhoto, this@PostActivity)

            file = photoFile
            binding.prevImageView.setImageURI(selectedPhoto)
        }
    }

    private fun openCamera() {
        createCustomTempFile(application).also { file ->
            val photoUri: Uri = FileProvider.getUriForFile(
                this@PostActivity,
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
            binding.prevImageView.setImageBitmap(bitmapResult)
        }
    }

    private fun showLoad(isLoad: Boolean) {
        binding.progressBar.visibility = if (isLoad) View.VISIBLE else View.GONE
    }
}