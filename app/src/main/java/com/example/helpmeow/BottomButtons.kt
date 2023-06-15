package com.example.helpmeow

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class BottomButtons : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom)

        val homeButton: ImageButton = findViewById(R.id.navigation_home)
        val breedButton: ImageButton = findViewById(R.id.navigation_breed)
        val favoriteButton: ImageButton = findViewById(R.id.navigation_favorite)
        val profileButton: ImageButton = findViewById(R.id.navigation_profile)
        val postButton: ImageButton = findViewById(R.id.navigation_post)

        homeButton.setOnClickListener(this)
        breedButton.setOnClickListener(this)
        favoriteButton.setOnClickListener(this)
        profileButton.setOnClickListener(this)
        postButton.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.navigation_home -> {
                // Handle click on home button
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            }
            R.id.navigation_breed -> {
                // Handle click on dashboard button
                val intent = Intent(this, BreedActivity::class.java)
                startActivity(intent)
            }
            R.id.navigation_favorite -> {
                // Handle click on notifications button
                val intent = Intent(this, FavoriteActivity::class.java)
                startActivity(intent)
            }
            R.id.navigation_profile -> {
                // Handle click on profile button
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
            }
            R.id.navigation_post -> {
                // Handle click on post button
                val intent = Intent(this, PostActivity::class.java)
                startActivity(intent)
            }
        }
    }
}