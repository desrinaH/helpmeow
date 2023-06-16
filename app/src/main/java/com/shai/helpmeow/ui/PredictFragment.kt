package com.shai.helpmeow.ui


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.shai.helpmeow.databinding.FragmentPredictBinding
import com.shai.helpmeow.model.UserPreference
import com.shai.helpmeow.model.ViewModelFactory

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "session")

class PredictFragment : Fragment() {

    private var _binding: FragmentPredictBinding? = null
    private lateinit var viewModel: PredictViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
//        val pref = UserPreference.getInstance(dataStore)
//        viewModel = ViewModelProvider(this, ViewModelFactory(pref))[PostViewModel::class.java]
//            ViewModelProvider(this).get(PredictViewModel::class.java)

        val pref = UserPreference.getInstance(requireContext().dataStore)
        viewModel = ViewModelProvider(this, ViewModelFactory(pref, requireContext()))[PredictViewModel::class.java]

        _binding = FragmentPredictBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}