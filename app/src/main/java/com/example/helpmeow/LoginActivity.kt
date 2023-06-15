package com.example.helpmeow

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatTextView
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.content.SharedPreferences
import android.preference.PreferenceManager


class LoginActivity : Activity() {


    private lateinit var et_email: EditText
    private lateinit var et_password: EditText
    private lateinit var google_btn: Button

    private var email: String? = null
    private var username: String? = null

    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        et_email = findViewById(R.id.enter_email)
        et_password = findViewById(R.id.enter_password)
        google_btn = findViewById(R.id.google_btn)


        val registerTextView: AppCompatTextView = findViewById(R.id.to_register)
        registerTextView.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }

        loginAction()
    }

    fun loginAction(){
        val loginButton = findViewById<TextView>(R.id.login_btn)
        loginButton.setOnClickListener {
            login()
        }
    }


    fun login() {
        val request = LoginRequest()
        request.email = et_email.text.toString().trim()
        request.password = et_password.text.toString().trim()

        val retro = Retro().getRetroClientInstance().create(LoginApi::class.java)
        retro.login(request).enqueue(object : Callback<LoginResponse> {
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(applicationContext, "100:Login Failure", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                val user = response.body()
                val yourId = user?.yourId

                email = user?.email
                username = user?.username

                if (response.isSuccessful) {
                    val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(applicationContext)
                    val editor = sharedPreferences.edit()
                    editor.putString("email", email)
                    editor.putString("username", username)
                    editor.putString("yourId", yourId)
                    editor.apply()

                    val intent = Intent(this@LoginActivity, ProfileActivity::class.java)
                    startActivity(intent)
                    finish()

                } else {
                    val errorBody = response.errorBody()?.string()
                    val errorMessage = try {
                        val errorJson = Gson().fromJson(errorBody, ErrorJson::class.java)
                        errorJson.message ?: "Unknown error"
                    } catch (e: Exception) {
                        e.printStackTrace()
                        errorBody ?: "Unknown error"
                    }
                    Toast.makeText(applicationContext, errorMessage, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

}

