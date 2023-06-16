package com.shai.helpmeow.ui

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.shai.helpmeow.databinding.ActivityLoginBinding
import com.shai.helpmeow.model.DataProgress
import com.shai.helpmeow.model.UserPreference
import com.shai.helpmeow.model.ViewModelFactory

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "session")

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel
    private lateinit var userViewModel: UserViewModel
    private lateinit var sharedPreferences: SharedPreferences

    private var email: String? = null
    private var username: String? = null

    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

//        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
//        userViewModel.userId.observe(this) { userId ->
//            // Gunakan User ID yang hyperbaric di sini
//            println("User ID: $userId")
//        }

//        sharedPreferences = getSharedPreferences("MyApp", MODE_PRIVATE)
//
//        val userId = "12345"
//        saveUserId(userId)

//        val sharedPreferences = getSharedPreferences("session", Context.MODE_PRIVATE)
//        val userId = "12345" // ID pengguna yang diperoleh setelah login
//        sharedPreferences.edit().putString("userId", userId).apply()

        retrieveSession()
        setupViewModel()
        setupView()
        setupAction()
        playAnimation()
    }

    private fun saveUserId(userId: String) {
        val editor = sharedPreferences.edit()
        editor.putString("userId", userId)
        editor.apply()
    }

    private fun retrieveSession() {
        val pref = UserPreference.getInstance(dataStore)
        val sessionFlow = pref.getSession()
        lifecycleScope.launchWhenStarted {
            sessionFlow.collect { session ->
                if (session.isNotEmpty()) {
                    startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                    finishAffinity()
                }
            }
        }
    }

    private fun setupAction() {
        binding.loginButton.setOnClickListener(::onLoginButtonClick)
        binding.registerButton.setOnClickListener(::onRegisterButtonClick)
    }

    private fun onLoginButtonClick(view: View) {
        val email = binding.emailEditText.text.toString()
        val password = binding.passwordEditText.text.toString()
        viewModel.login(email, password)
    }

    private fun onRegisterButtonClick(view: View) {
        startActivity(Intent(this, RegisterActivity::class.java))
    }

    private fun playAnimation() {
        showLoad(false)
        val title = ObjectAnimator.ofFloat(binding.titleTextView, View.ALPHA, 1f).setDuration(500)
        val message =
            ObjectAnimator.ofFloat(binding.messageTextView, View.ALPHA, 1f).setDuration(500)
        val emailEditTextLayout =
            ObjectAnimator.ofFloat(binding.emailEditTextLayout, View.ALPHA, 1f).setDuration(500)
        val passwordEditTextLayout =
            ObjectAnimator.ofFloat(binding.passwordEditTextLayout, View.ALPHA, 1f).setDuration(500)
        val login = ObjectAnimator.ofFloat(binding.loginButton, View.ALPHA, 1f).setDuration(500)
        val acc = ObjectAnimator.ofFloat(binding.haveAcc, View.ALPHA, 1f).setDuration(500)
        val register =
            ObjectAnimator.ofFloat(binding.registerButton, View.ALPHA, 1f).setDuration(500)

        AnimatorSet().apply {
            playSequentially(
                title,
                message,
                emailEditTextLayout,
                passwordEditTextLayout,
                login,
                acc,
                register
            )
        }.start()
    }

    private fun setupView() {
        viewModel.login.observe(this) { data ->
            when (data) {
                is DataProgress.Loading -> handleLoading()
                is DataProgress.Success -> handleSuccess(data.resultData)
                is DataProgress.Error -> handleError(data.errorMessage)
            }
        }
    }

    private fun handleSuccess(userId: String?) {
//        showLoad(false)
////        if (userId != null) {
////            saveUserId(userId)
////        }
//        val intent = Intent(this, MainActivity::class.java)
////        intent.putExtra("userId", userId)
//        startActivity(intent)
//        finish()
        showLoad(false)

        startActivity(Intent(this, HomeActivity::class.java))
        finishAffinity()
    }

    private fun handleLoading() {
        showLoad(true)
    }

    private fun handleError(errorMessage: String?) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        showLoad(false)
    }

//    private fun saveUserId(userId: String): String? {
//        val sharedPreferences = getSharedPreferences("HelpMeow", Context.MODE_PRIVATE)
//        return sharedPreferences.getString("userId", null)
//    }

    private fun setupViewModel() {
        val pref = UserPreference.getInstance(dataStore)
        viewModel = ViewModelProvider(this, ViewModelFactory(pref, this))[LoginViewModel::class.java]
    }

    private fun showLoad(isLoad: Boolean) {
        binding.progressBar.visibility = if (isLoad) View.VISIBLE else View.GONE
    }
}