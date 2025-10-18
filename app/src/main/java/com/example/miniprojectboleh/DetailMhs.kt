package com.example.miniprojectboleh

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import com.example.miniprojectboleh.databinding.DetailMhsBinding

class DetailMhs : AppCompatActivity() {
    private lateinit var binding: DetailMhsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetailMhsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //spinner
        val listSpinner = arrayOf("About me","My Course","My Experiences")

        //adapter spinner
        val spinAdapter = android.widget.ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, listSpinner)
        binding.spinner.adapter = spinAdapter

//
//        val nama = intent.getStringExtra("nama")
//        val nrp = intent.getStringExtra("nrp")
//        val prodi = intent.getStringExtra("prodi")
//        val imgId = intent.getIntExtra("imgId", 0)

        val index = intent.getIntExtra(ListMhs.MHS_INDEX, 0)
        val mhs = DataMhs.profile[index]

        binding.txtNamaDetail.text = mhs.nama
        binding.txtNrpDetail.text = mhs.nrp
        binding.txtProdiDetail.text = mhs.prodi
        binding.imgDetail.setImageResource(mhs.imgId)

        //mengubah paragraf klau spiner diubah
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                binding.textSpinner.text = when (position) {
                    0 -> "about me"
                    1 -> "my course"
                    else -> "Pengalaman"
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) { /* no-op */ }
        }

        when {
            mhs.prodi.contains("DSAI", ignoreCase = true) -> binding.rbDSAI.isChecked = true
            mhs.prodi.contains("NCS", ignoreCase = true) -> binding.rbNCS.isChecked = true
            mhs.prodi.contains("IMES", ignoreCase = true) -> binding.rbIMES.isChecked = true
            mhs.prodi.contains("DMT", ignoreCase = true) -> binding.rbDMT.isChecked = true
            mhs.prodi.contains("GD", ignoreCase = true) -> binding.rbGD.isChecked = true
        }
    }
}