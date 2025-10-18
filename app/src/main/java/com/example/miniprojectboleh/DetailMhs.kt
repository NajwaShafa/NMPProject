package com.example.miniprojectboleh

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.miniprojectboleh.databinding.DetailMhsBinding

class DetailMhs : AppCompatActivity() {
    private lateinit var binding: DetailMhsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetailMhsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nama = intent.getStringExtra("nama")
        val nrp = intent.getStringExtra("nrp")
        val prodi = intent.getStringExtra("prodi")
        val imgId = intent.getIntExtra("imgId", 0)

        binding.txtNamaDetail.text = nama
        binding.txtNrpDetail.text = nrp
        binding.txtProdiDetail.text = prodi
        binding.imgDetail.setImageResource(imgId)
    }
}