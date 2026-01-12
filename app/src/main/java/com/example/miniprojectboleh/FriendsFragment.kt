package com.example.miniprojectboleh

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.miniprojectboleh.adapter.MhsAdapter
import com.example.miniprojectboleh.databinding.FragmentFriendsBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class FriendsFragment : Fragment() {
    // Menggunakan View Binding untuk Fragment
    private var _binding: FragmentFriendsBinding? = null
    private val binding get() = _binding!!
    private var friends: ArrayList<Mahasiswa> = ArrayList()
    private lateinit var adapter: MhsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFriendsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Konfigurasi RecyclerView
        binding.rvFriends.layoutManager = LinearLayoutManager(context)
        adapter = MhsAdapter(friends)
        binding.rvFriends.adapter = adapter

        // Ambil data teman dari database
        fetchFriends()
    }

    // Fungsi untuk mengambil data menggunakan Volley
    fun fetchFriends() {
        val q = Volley.newRequestQueue(context)
        val url = "http://10.0.2.2/project_uas/get_friend.php"

        val stringRequest = StringRequest(Request.Method.GET, url,
            { response ->
                // Mengonversi JSON Array menjadi ArrayList Mahasiswa menggunakan Gson
                val sType = object : TypeToken<ArrayList<Mahasiswa>>() {}.type
                val result = Gson().fromJson<ArrayList<Mahasiswa>>(response, sType)

                friends.clear()
                friends.addAll(result)
                adapter.notifyDataSetChanged()
            },
            { error -> Log.e("api_error", error.message.toString()) }
        )
        q.add(stringRequest)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}