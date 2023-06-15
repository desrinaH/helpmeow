package com.example.helpmeow

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class BreedInfoActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_BREED_ITEM = "extra_breed_item"
    }

    private lateinit var breedItem: BreedItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_breed_info)

        breedItem = intent?.getParcelableExtra(EXTRA_BREED_ITEM) as? BreedItem
            ?: throw IllegalArgumentException("BreedItem must be provided in the intent extras")

        // Populate the views in the layout with the breed information
        val imageView: ImageView = findViewById(R.id.gambarKucing)
        val textView: TextView = findViewById(R.id.detailCat4)
        val detailInfoTextView: TextView = findViewById(R.id.detailInfoTextView)
        val priceTextView: TextView = findViewById(R.id.priceTextView)
        val weightTextView: TextView = findViewById(R.id.weightTextView)

        imageView.setImageResource(breedItem.imageResId)
        textView.text = breedItem.breed
        detailInfoTextView.text = breedItem.detailInfo
        priceTextView.text = breedItem.price
        weightTextView.text = breedItem.weight
    }
}