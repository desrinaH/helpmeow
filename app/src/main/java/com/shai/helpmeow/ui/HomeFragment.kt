package com.shai.helpmeow.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.Fragment
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

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "session")

class HomeFragment : Fragment() {

    //    private var _binding: FragmentHomeBinding? = null
//    private val binding get() = _binding!!
    private lateinit var homeAdapter: HomeAdapter

    //    private lateinit var viewModel: HomeViewModel
    lateinit var linearLayoutManager: LinearLayoutManager

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setHasOptionsMenu(true)
//
//        setupViewModel()
//        setupView()
//    }

//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        binding = FragmentHomeBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        _binding = FragmentHomeBinding.inflate(inflater, container, false)
//        return binding.root
//    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        var recyclerviewUsers = view.findViewById<RecyclerView>(R.id.postRecyclerView)

        recyclerviewUsers.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(requireContext())
        recyclerviewUsers.layoutManager = linearLayoutManager

        getMyData()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

//        setupViewModel()
//        setupView()
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_home_option, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                val intent = Intent(requireContext(), HomeActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getMyData() {
        var recyclerviewUsers = view?.findViewById<RecyclerView>(R.id.postRecyclerView)
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiService::class.java)

        val retrofitData = retrofitBuilder.getPost()

        retrofitData.enqueue(object : Callback<List<Post>?> {
            override fun onResponse(call: Call<List<Post>?>, response: Response<List<Post>?>) {
                val responseBody = response.body()!!

                homeAdapter = HomeAdapter(requireContext(), responseBody)
                homeAdapter.notifyDataSetChanged()
                if (recyclerviewUsers != null) {
                    recyclerviewUsers.adapter = homeAdapter
                }
            }

            override fun onFailure(call: Call<List<Post>?>, t: Throwable) {
                Toast.makeText(requireContext(), "onFailure", Toast.LENGTH_SHORT).show()
            }
        })
    }

//    private fun setupView() {
//        homeAdapter = HomeAdapter()
//        binding.postRecyclerView.apply {
//            setHasFixedSize(true)
//            adapter = homeAdapter
//        }
//
////        observePostData()
//        CoroutineScope(Dispatchers.IO).launch {
//            viewModel.getPost()
//        }

//        viewModel.getUser().observe(viewLifecycleOwner) { token ->
//            if (token.isEmpty()) {
//                observePostData()
//                CoroutineScope(Dispatchers.IO).launch {
//                    viewModel.getPost()
//                }
//            }
//        }
//    }

//    private fun observePostData() {
//        viewModel.post.observe(viewLifecycleOwner) { data ->
//            when (data) {
//                is DataProgress.Success -> handleSuccess(data.resultData as List<Post>)
//                is DataProgress.Loading -> handleLoading()
//                is DataProgress.Error -> handleError(data.errorMessage)
//            }
//        }
//    }
//
//    private fun handleSuccess(post: List<Post>) {
//        showLoad(false)
//        homeAdapter.setData(post)
//    }
//
//    private fun handleLoading() {
//        showLoad(true)
//    }
//
//    private fun handleError(errorMessage: String?) {
//        Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
//        showLoad(false)
//    }

//    private fun setupViewModel() {
//        val pref = UserPreference.getInstance(requireContext().dataStore)
//        val viewModelFactory = ViewModelFactory(pref, requireContext())
//
//        viewModel = ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]
//    }
//
//    private fun showLoad(isLoad: Boolean) {
//        binding.progressBar.visibility = if (isLoad) View.VISIBLE else View.GONE
//    }

//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
}