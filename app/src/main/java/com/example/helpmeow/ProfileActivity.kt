package com.example.helpmeow

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.provider.MediaStore
import android.widget.*
import com.google.gson.Gson
import de.hdodenhof.circleimageview.CircleImageView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileActivity : AppCompatActivity() {

    private val bottomButtons: BottomButtons = BottomButtons()
    private lateinit var nameTextView: TextView
    private lateinit var emailTextView: TextView
    private lateinit var sharedPref: SharedPreferences

    private lateinit var logoutBtn: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)


        nameTextView = findViewById(R.id.nameTextView)
        emailTextView = findViewById(R.id.emailTextView)

        sharedPref = PreferenceManager.getDefaultSharedPreferences(applicationContext)

        findViewById<ImageButton>(R.id.navigation_home).setOnClickListener(bottomButtons)
        findViewById<ImageButton>(R.id.navigation_breed).setOnClickListener(bottomButtons)
        findViewById<ImageButton>(R.id.navigation_favorite).setOnClickListener(bottomButtons)
        findViewById<ImageButton>(R.id.navigation_profile).setOnClickListener(bottomButtons)
        findViewById<ImageButton>(R.id.navigation_post).setOnClickListener(bottomButtons)


        val galleryButton2 = findViewById<ImageView>(R.id.photoImageView)
        galleryButton2.setOnClickListener {
            openGallery2()
        }

        showProfile()
        logoutAction()
    }

    private fun openGallery2() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startActivityForResult(intent, GALLERY_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GALLERY_REQUEST_CODE && resultCode == RESULT_OK) {
            val selectedImageUri = data?.data
            val imageView = findViewById<ImageView>(R.id.photoImageView)
            imageView.setImageURI(selectedImageUri)
        }
    }

    companion object {
        private const val GALLERY_REQUEST_CODE = 1
    }

    fun logoutAction(){
        val logoutButton = findViewById<TextView>(R.id.Logout)
        logoutButton.setOnClickListener {
            logout()
        }
    }

    private fun logout() {
        val logoutApi = Retro().getRetroClientInstance().create(LogoutApi::class.java)

        val id = sharedPref.getString("yourId", "")

        if (!id.isNullOrEmpty()) {
            logoutApi.logout(id).enqueue(object : Callback<LogoutResponse> {
                override fun onResponse(call: Call<LogoutResponse>, response: Response<LogoutResponse>) {
                    if (response.isSuccessful) {
                        // Handle successful logout
                        Toast.makeText(this@ProfileActivity, "Logout Success", Toast.LENGTH_SHORT).show()

                        val intent = Intent(this@ProfileActivity, LoginActivity::class.java)
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

                override fun onFailure(call: Call<LogoutResponse>, t: Throwable) {
                    // Handle failure in making the logout API call
                    Toast.makeText(this@ProfileActivity, "Logout Failure", Toast.LENGTH_SHORT).show()
                }
            })
        } else {
            Toast.makeText(this@ProfileActivity, "User ID not available", Toast.LENGTH_SHORT).show()
        }
    }

    fun showProfile() {
        val email = sharedPref.getString("email", "")
        val username = sharedPref.getString("username", "")

        nameTextView.text = username
        emailTextView.text = email
    }

    inner class BottomButtons : View.OnClickListener {
        override fun onClick(view: View) {
            when (view.id) {
                R.id.navigation_home -> {
                    val intent = Intent(this@ProfileActivity, HomeActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                R.id.navigation_breed -> {
                    val intent = Intent(this@ProfileActivity, BreedActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                R.id.navigation_favorite -> {
                    val intent = Intent(this@ProfileActivity, FavoriteActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                R.id.navigation_profile -> {
                    val intent = Intent(this@ProfileActivity, ProfileActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                R.id.navigation_post -> {
                    val intent = Intent(this@ProfileActivity, PostActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }
}





