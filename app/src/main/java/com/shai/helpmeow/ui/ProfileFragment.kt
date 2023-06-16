package com.shai.helpmeow.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.Fragment
import com.shai.helpmeow.R
import com.shai.helpmeow.databinding.FragmentProfileBinding



class ProfileFragment : Fragment() {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "session")
    private lateinit var binding: FragmentProfileBinding
    private lateinit var viewModel: ProfileViewModel

    private lateinit var nameTextView: TextView
    private lateinit var emailTextView: TextView
    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        observeProfileData()
        setHasOptionsMenu(true)

//        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
//
//        val userId = getUserId()
//        if (userId != null) {
//            viewModel.fetchProfileData(userId)
//        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        nameTextView = view.findViewById(R.id.nameTextView)
        emailTextView = view.findViewById(R.id.emailTextView)

        sharedPref = PreferenceManager.getDefaultSharedPreferences(requireContext())

        val galleryButton2 = view.findViewById<ImageView>(R.id.photoImageView)
        galleryButton2.setOnClickListener {
            openGallery2()
        }

        showProfile()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        observeProfileData()
    }

    private fun openGallery2() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startActivityForResult(intent, GALLERY_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GALLERY_REQUEST_CODE && resultCode == AppCompatActivity.RESULT_OK) {
            val selectedImageUri = data?.data
            val imageView = view?.findViewById<ImageView>(R.id.photoImageView)
            if (imageView != null) {
                imageView.setImageURI(selectedImageUri)
            }
        }
    }

    companion object {
        private const val GALLERY_REQUEST_CODE = 1
    }


    fun showProfile() {
        val email = sharedPref.getString("email", "")
        val username = sharedPref.getString("username", "")

        nameTextView.text = username
        emailTextView.text = email
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_profile_option, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.settings -> {
                val intent = Intent(requireContext(), SettingsActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun getUserId(): String? {
        val sharedPreferences = requireContext().getSharedPreferences("MyApp", Context.MODE_PRIVATE)
        return sharedPreferences.getString("userId", null)
    }

    private fun saveUserId(userId: String) {
        val sharedPreferences = requireContext().getSharedPreferences("MyApp", Context.MODE_PRIVATE)
        sharedPreferences.edit().putString("userId", userId).apply()
    }

//    private fun observeProfileData() {
//        viewModel.profileResult.observe(viewLifecycleOwner) { result ->
//            when (result) {
//                is DataProgress.Success -> {
//                    val profile = result.resultData
//                    if (profile != null) {
//                        binding.nameTextView.text = profile.username
//                        binding.emailTextView.text = profile.email
//                    }
//                }
//                is DataProgress.Error -> {
//                    val errorMessage = result.errorMessage
//                    if (!errorMessage.isNullOrEmpty()) {
//                        Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
//                    }
//                }
//                // Handle loading state if needed
//                is DataProgress.Loading -> {
//                    // You can show a loading indicator here if needed
//                }
//            }
//        }
//    }
}

