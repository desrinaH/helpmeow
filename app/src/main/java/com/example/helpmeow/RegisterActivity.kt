package com.example.helpmeow

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : Activity() {


    private lateinit var et_name: EditText
    private lateinit var et_email_register: EditText
    private lateinit var et_password_register: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val registerTextView: TextView = findViewById(R.id.to_login)
        registerTextView.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        et_name = findViewById(R.id.enter_name)
        et_email_register = findViewById(R.id.enter_email)
        et_password_register = findViewById(R.id.enter_password)

        registerAction()
    }

    private fun registerAction() {
        val registerButton = findViewById<TextView>(R.id.register_btn)

        registerButton?.setOnClickListener {
            register()
        }
    }

    private fun register() {
        val request = RegisterRequest()
        request.email = et_email_register.text.toString().trim()
        request.password = et_password_register.text.toString().trim()
        request.username = et_name.text.toString().trim()

        val retro = Retro().getRetroClientInstance().create(RegisterApi::class.java)
        retro.register(request).enqueue(object : Callback<RegisterResponse> {
            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                Toast.makeText(applicationContext, "101:Register Failure", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                val user = response.body()
                val email = user?.email
                val yourId = user?.yourId
                val username = user?.username

                if (response.isSuccessful) {
                    val message = response.body()?.message
                    Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()

                    val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
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