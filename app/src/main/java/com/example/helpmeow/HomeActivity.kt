package com.example.helpmeow

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://backend-dot-helpmeow.et.r.appspot.com/"
class HomeActivity : Activity() {

    lateinit var homeAdapter: HomeAdapter
    lateinit var linearLayoutManager: LinearLayoutManager

    private val bottomButtons: BottomButtons = BottomButtons()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_card)

        findViewById<ImageButton>(R.id.navigation_home).setOnClickListener(bottomButtons)
        findViewById<ImageButton>(R.id.navigation_breed).setOnClickListener(bottomButtons)
        findViewById<ImageButton>(R.id.navigation_favorite).setOnClickListener(bottomButtons)
        findViewById<ImageButton>(R.id.navigation_profile).setOnClickListener(bottomButtons)
        findViewById<ImageButton>(R.id.navigation_post).setOnClickListener(bottomButtons)

        var recyclerviewUsers= findViewById<RecyclerView>(R.id.recyclerview_users)

        recyclerviewUsers.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(this)
        recyclerviewUsers.layoutManager=linearLayoutManager

        getMyData()


    }

    private fun getMyData(){
        var recyclerviewUsers= findViewById<RecyclerView>(R.id.recyclerview_users)
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(CatApi::class.java)

        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<List<CatDataPostItem>?> {
            override fun onResponse(call: Call<List<CatDataPostItem>?>, response: Response<List<CatDataPostItem>?>
            ) {
                val responseBody = response.body()!!

                homeAdapter = HomeAdapter(baseContext,responseBody)
                homeAdapter.notifyDataSetChanged()
                recyclerviewUsers.adapter = homeAdapter


            }

            override fun onFailure(call: Call<List<CatDataPostItem>?>, t: Throwable) {
                Toast.makeText(applicationContext, "onFailure", Toast.LENGTH_SHORT).show()
            }
        })
    }



    inner class BottomButtons : View.OnClickListener {
        override fun onClick(view: View) {
            when (view.id) {
                R.id.navigation_home -> {
                    val intent = Intent(this@HomeActivity, HomeActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                R.id.navigation_breed -> {
                    val intent = Intent(this@HomeActivity, BreedActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                R.id.navigation_favorite -> {
                    val intent = Intent(this@HomeActivity, FavoriteActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                R.id.navigation_profile -> {
                    val intent = Intent(this@HomeActivity, ProfileActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                R.id.navigation_post -> {
                    val intent = Intent(this@HomeActivity, PostActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }
}