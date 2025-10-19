package com.example.miniprojectboleh

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.miniprojectboleh.databinding.DetailMhsBinding

class DetailMhs : AppCompatActivity() {
    private lateinit var binding: DetailMhsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetailMhsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Ambil index dari intent
        val index = intent.getIntExtra(ListMhs.MHS_INDEX, -1)
        if (index == -1 || index >= DataMhs.profile.size) {
            Toast.makeText(this, "Data mahasiswa tidak ditemukan", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        // Ambil data sesuai index
        val mhs = DataMhs.profile[index]

        // Tampilkan data ke layout
        binding.txtNamaDetail.text = mhs.nama
        binding.txtNrpDetail.text = mhs.nrp
        binding.txtProdiDetail.text = mhs.prodi
        binding.imgDetail.setImageResource(mhs.imgId)

        // Pilih radio button sesuai prodi
        when {
            mhs.prodi.contains("DSAI", ignoreCase = true) -> binding.rbDSAI.isChecked = true
            mhs.prodi.contains("NCS", ignoreCase = true)  -> binding.rbNCS.isChecked = true
            mhs.prodi.contains("IMES", ignoreCase = true) -> binding.rbIMES.isChecked = true
            mhs.prodi.contains("DMT", ignoreCase = true)  -> binding.rbDMT.isChecked = true
            mhs.prodi.contains("GD", ignoreCase = true)   -> binding.rbGD.isChecked = true
        }

        // Spinner setup
        val spinnerItems = resources.getStringArray(R.array.spinner_info)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerItems)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter = adapter

        // Teks untuk tiap opsi spinner
        val about = "Halo, saya ${mhs.nama}. Saya mahasiswa ${mhs.prodi}."
        val course = "Contoh mata kuliah: Struktur Data, Basis Data, Mobile Programming."
        val exp = "Pengalaman: Asisten Lab, Magang, Proyek AI."

        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                binding.textSpinner.text = when (pos) {
                    1 -> about
                    2 -> course
                    3 -> exp
                    else -> ""
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        // Default: placeholder
        binding.spinner.setSelection(0, false)
        binding.textSpinner.text = ""

        // Tombol Request Friend
        binding.btnRequestFriend.setOnClickListener {
            Toast.makeText(this, "Request sent to ${mhs.nama}!", Toast.LENGTH_SHORT).show()
        }
    }
}
