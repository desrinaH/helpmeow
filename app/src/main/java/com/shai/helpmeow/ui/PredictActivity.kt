package com.shai.helpmeow.ui

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.shai.helpmeow.R
import com.shai.helpmeow.databinding.ActivityPredictBinding
import com.shai.helpmeow.model.UserPreference
import com.shai.helpmeow.model.ViewModelFactory
import com.shai.helpmeow.model.reduceFileImage
import com.shai.helpmeow.model.rotateBitmap
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File


private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "session")

class PredictActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPredictBinding
    private lateinit var viewModel: PredictViewModel
    private lateinit var file: Bitmap
    private var getFile: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val actionBar: ActionBar? = supportActionBar
        actionBar?.apply {
            setTitle(R.string.title_lens)
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        }
        binding = ActivityPredictBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()
        showLoading(false)
        showResultView(false)
        setupCamera()
        buttonHandler()
    }

    private fun buttonHandler() {

        binding.btnUpload.setOnClickListener {
            showLoading(true)
            uploadFileImage()
            getResult()
        }
    }

    private fun uploadFileImage() {
        //files
        val file = reduceFileImage(getFile as File)
        val requestImageFile = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
        val imageMultipart: MultipartBody.Part = MultipartBody.Part.createFormData(
            "file", file.name, requestImageFile
        )
        viewModel.getPredictPet(imageMultipart)
    }

    private fun getResult() {
        viewModel.dataPredict.observe(this) { it ->
            if (it != null) {
                showResultView(true)
                if (it.error == null) {
                    setDataResult(it.prediction)
                }
            }
        }
    }

    private fun setDataResult(breed: String?) {
        binding.tvResultRas.text = breed
//        binding.tvResultPresentase.text = "$percentage%"
    }

    private fun setupCamera() {
        val myFile = intent?.getSerializableExtra("picture") as File
        val isBackCamera = intent.getBooleanExtra("isBackCamera", true)
        val result = rotateBitmap(
            BitmapFactory.decodeFile(myFile.path),
            isBackCamera
        )
        getFile = myFile

        binding.previewImage.load(result) {
            crossfade(true)
            crossfade(1000)
        }
    }

    private fun setupViewModel() {
        val pref = UserPreference.getInstance(dataStore)
        viewModel = ViewModelProvider(this, ViewModelFactory(pref, this))[PredictViewModel::class.java]
    }

    private fun resultHandler() {
        //TODO: result FROM ML
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
            binding.tvProgress.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
            binding.tvProgress.visibility = View.GONE
        }
    }

    private fun showResultView(b: Boolean) {
        if (b) {
            binding.layoutResult.visibility = View.VISIBLE
            binding.btnUpload.visibility = View.GONE
            showLoading(false)
        } else {
            binding.layoutResult.visibility = View.GONE
            showLoading(true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onContextItemSelected(item)
    }

    public override fun onResume() {
        super.onResume()
        hideSystemUI()
    }

    private fun hideSystemUI() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
        showLoading(false)
    }
}