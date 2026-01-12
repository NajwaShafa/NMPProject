package com.example.miniprojectboleh

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.miniprojectboleh.adapter.MhsAdapter
import com.example.miniprojectboleh.databinding.FragmentHomeBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var students: ArrayList<Mahasiswa> = ArrayList()
    private lateinit var adapter: MhsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup RecyclerView
        binding.rvMhs.layoutManager = LinearLayoutManager(context)
        binding.rvMhs.setHasFixedSize(true)

        // Inisialisasi adapter dengan list kosong
        adapter = MhsAdapter(students)
        binding.rvMhs.adapter = adapter

        // Ambil data dari database eksternal
        fetchStudents()
    }

    private fun fetchStudents() {
        val q = Volley.newRequestQueue(context)
        val url = "http://10.0.2.2/project_uas/get_all_student.php"

        val stringRequest = StringRequest(Request.Method.GET, url,
            { response ->
                // Mengubah JSON Array menjadi ArrayList<Mahasiswa> menggunakan Gson
                val sType = object : TypeToken<ArrayList<Mahasiswa>>() {}.type
                val result = Gson().fromJson<ArrayList<Mahasiswa>>(response, sType)

                students.clear()
                students.addAll(result)
                adapter.notifyDataSetChanged() // Refresh RecyclerView
            },
            { error -> Log.e("api_error", error.message.toString()) }
        )
        q.add(stringRequest)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Mencegah memory leak pada Fragment
    }
}