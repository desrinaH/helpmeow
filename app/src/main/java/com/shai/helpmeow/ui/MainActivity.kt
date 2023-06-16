package com.shai.helpmeow.ui

import android.content.Context
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.shai.helpmeow.R
import com.shai.helpmeow.databinding.ActivityMainBinding
import com.shai.helpmeow.model.UserPreference
import com.shai.helpmeow.model.ViewModelFactory

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "session")

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
//    private lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionBar: ActionBar? = supportActionBar
        val color = ContextCompat.getColor(this, R.color.light_orange)
        actionBar?.setBackgroundDrawable(ColorDrawable(color))

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_info,
                R.id.navigation_predict,
                R.id.navigation_favorite,
                R.id.navigation_profile
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val userId = intent.getStringExtra("userId")
        if (userId != null) {
            saveUserId(userId)
        }

//        setupView()

    }

//    private fun setupViewM() {
//        mainViewModel.getUser().observe(this) { token ->
//            if (token.isNotEmpty()) {
////                observePostData()
//                CoroutineScope(Dispatchers.IO).launch {
//                    homeViewModel.getPost()
//                }
//            } else {
//                startActivity(Intent(this, LoginActivity::class.java))
//                finish()
//            }
//        }
//    }
    fun onLens(item: MenuItem) {
        startActivity(Intent(this, PredictCameraActivity::class.java))
        overridePendingTransition(0, 0)
    }

    private fun setupView() {
        val pref = UserPreference.getInstance(dataStore)
        mainViewModel = ViewModelProvider(this, ViewModelFactory(pref, this))[MainViewModel::class.java]

        mainViewModel.getUser().observe(this) { token ->
            token
            if (token.isEmpty() || token == "random token") {
                mainViewModel.logout()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }
    }

    private fun saveUserId(userId: String): String? {
        val sharedPreferences = getSharedPreferences("HelpMeow", Context.MODE_PRIVATE)
        return sharedPreferences.getString("userId", null)
    }

}