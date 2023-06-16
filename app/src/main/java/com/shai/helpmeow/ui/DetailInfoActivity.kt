package com.shai.helpmeow.ui

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.shai.helpmeow.R
import com.shai.helpmeow.data.BreedCat

class DetailInfoActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_BREED_ITEM = "extra_breed_item"
    }

    private lateinit var breed: BreedCat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_info)

        breed = intent?.getParcelableExtra(EXTRA_BREED_ITEM) as? BreedCat
            ?: throw IllegalArgumentException("BreedItem must be provided in the intent extras")

        // Populate the views in the layout with the breed information
        val imageView: ImageView = findViewById(R.id.gambarKucing)
        val textView: TextView = findViewById(R.id.detailCat4)
        val detailInfoTextView: TextView = findViewById(R.id.detailInfoTextView)
        val priceTextView: TextView = findViewById(R.id.priceTextView)
        val weightTextView: TextView = findViewById(R.id.weightTextView)

        imageView.setImageResource(breed.imageResId)
        textView.text = breed.breed
        detailInfoTextView.text = breed.detailInfo
        priceTextView.text = breed.price
        weightTextView.text = breed.weight
    }
}