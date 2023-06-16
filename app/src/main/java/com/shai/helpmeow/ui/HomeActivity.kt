package com.shai.helpmeow.ui

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shai.helpmeow.R
import com.shai.helpmeow.data.ApiService
import com.shai.helpmeow.data.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://backend-dot-helpmeow.et.r.appspot.com/"
class HomeActivity : AppCompatActivity() {

    lateinit var homeAdapter: HomeAdapter
    lateinit var linearLayoutManager: LinearLayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val actionBar: androidx.appcompat.app.ActionBar? = supportActionBar
        val color = ContextCompat.getColor(this, R.color.light_orange)
        actionBar?.setBackgroundDrawable(ColorDrawable(color))

        var recyclerviewUsers= findViewById<RecyclerView>(R.id.postRecyclerView)

        recyclerviewUsers.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(this)
        recyclerviewUsers.layoutManager=linearLayoutManager

        getMyData()


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_activity, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.post -> {
                Intent(this, PostActivity::class.java).also {
                    startActivity(it)
                }
            }
            R.id.cat -> {
                Intent(this, MainActivity::class.java).also {
                    startActivity(it)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getMyData(){
        var recyclerviewUsers= findViewById<RecyclerView>(R.id.postRecyclerView)
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiService::class.java)

        val retrofitData = retrofitBuilder.getPost()

        retrofitData.enqueue(object : Callback<List<Post>?> {
            override fun onResponse(call: Call<List<Post>?>, response: Response<List<Post>?>
            ) {
                val responseBody = response.body()!!

                homeAdapter = HomeAdapter(baseContext,responseBody)
                homeAdapter.notifyDataSetChanged()
                recyclerviewUsers.adapter = homeAdapter


            }

            override fun onFailure(call: Call<List<Post>?>, t: Throwable) {
                Toast.makeText(applicationContext, "onFailure", Toast.LENGTH_SHORT).show()
            }
        })
    }

}