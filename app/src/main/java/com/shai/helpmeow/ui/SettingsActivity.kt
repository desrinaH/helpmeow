package com.shai.helpmeow.ui

import android.content.Context
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.shai.helpmeow.R
import com.shai.helpmeow.databinding.ActivitySettingsBinding
import com.shai.helpmeow.model.UserPreference
import com.shai.helpmeow.model.ViewModelFactory

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "session")

class SettingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBinding
    private lateinit var viewModel: SettingsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionBar: ActionBar? = supportActionBar
        val color = ContextCompat.getColor(this, R.color.light_orange)
        actionBar?.setBackgroundDrawable(ColorDrawable(color))

        setupAction()
        setupViewModel()
    }

    private fun setupAction() {
        binding.logoutButton.setOnClickListener(::onLogoutButtonClick)
    }

    private fun onLogoutButtonClick(view: View) {
        viewModel.logout()
        startActivity(Intent(this, LoginActivity::class.java))
        finishAffinity()
    }

    private fun setupViewModel() {
        val pref = UserPreference.getInstance(dataStore)
        viewModel = ViewModelProvider(this, ViewModelFactory(pref, this))[SettingsViewModel::class.java]
    }
}