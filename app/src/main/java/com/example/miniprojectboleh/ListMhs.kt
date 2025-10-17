package com.example.miniprojectboleh

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.miniprojectboleh.databinding.ActivityListMhsBinding

class ListMhs : AppCompatActivity() {

    private lateinit var binding: ActivityListMhsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListMhsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvMhs.layoutManager = LinearLayoutManager(this)
    }
}