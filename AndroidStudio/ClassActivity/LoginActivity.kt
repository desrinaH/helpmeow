package com.example.helpmeow

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatTextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : Activity() {


    private lateinit var et_email: EditText
    private lateinit var et_password: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        et_email = findViewById(R.id.enter_email)
        et_password = findViewById(R.id.enter_password)


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

        val retro = Retro().getRetroClientInstance().create(UserApi::class.java)
        retro.login(request).enqueue(object : Callback<LoginResponse> {
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(applicationContext, "100:Login Failure", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                val user = response.body()
                val email = user?.email
                val yourId = user?.yourId

                if (response.isSuccessful) {
                    if (email != null) {
                        Log.e("email", email)
                    }
                    if (yourId != null) {
                        Log.e("your_id", yourId)
                    }
                    Toast.makeText(applicationContext, "Login Success", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(applicationContext, "Login Failed", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}