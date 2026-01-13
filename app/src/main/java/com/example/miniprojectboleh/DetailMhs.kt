package com.example.miniprojectboleh

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.miniprojectboleh.databinding.DetailMhsBinding
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import org.json.JSONObject

class DetailMhs : AppCompatActivity() {
    private lateinit var binding: DetailMhsBinding
    private var nrpMahasiswa: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetailMhsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Ambil NRP dari Intent
        nrpMahasiswa = intent.getStringExtra("NRP") ?: ""

        if (nrpMahasiswa.isNotEmpty()) {
            ambilDetailMahasiswa()
        }

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnRequestFriend.setOnClickListener {
            tambahTemanBaru()
        }
    }

    private fun ambilDetailMahasiswa() {
        val antrianRequest = Volley.newRequestQueue(this)
        val alamatUrl = "http://10.0.2.2/NMPProject/get_student_id.php?nrp=$nrpMahasiswa"

        val stringRequest = StringRequest(Request.Method.GET, alamatUrl,
            { respon ->
                try {
                    val dataMhs = Gson().fromJson(respon, Mahasiswa::class.java)

                    if (dataMhs != null) {
                        tampilkanDataProfil(dataMhs)
                    } else {
                        Toast.makeText(this, "Data tidak ditemukan di database", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: Exception) {
                    Log.e("GALAT_JSON", "Gagal memproses data: ${e.message}")
                }
            },
            { error ->
                Log.e("GALAT_VOLLEY", error.message.toString())
                Toast.makeText(this, "Gagal terhubung ke server", Toast.LENGTH_SHORT).show()
            }
        )
        antrianRequest.add(stringRequest)
    }

    private fun tampilkanDataProfil(mhs: Mahasiswa) {
        binding.txtNamaDetail.text = mhs.nama
        binding.txtNrpDetail.text = mhs.nrp
        binding.txtProdiDetail.text = mhs.program

        // Muat Foto dengan Picasso
        Picasso.get().load(mhs.photo_url).into(binding.imgDetail)

        // Cek RadioButton prodi
        when (mhs.program) {
            "DSAI" -> binding.rbDSAI.isChecked = true
            "GD" -> binding.rbGD.isChecked = true
            "DMT" -> binding.rbDMT.isChecked = true
            "IMES" -> binding.rbIMES.isChecked = true
            "NCS" -> binding.rbNCS.isChecked = true
        }

        // Atur Spinner Informasi
        val daftarInfo = resources.getStringArray(R.array.spinner_info)
        val adapterInfo = ArrayAdapter(this, android.R.layout.simple_spinner_item, daftarInfo)
        adapterInfo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter = adapterInfo

        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, posisi: Int, id: Long) {
                binding.textSpinner.text = when (posisi) {
                    1 -> mhs.about_me
                    2 -> mhs.my_course
                    3 -> mhs.my_experiences
                    else -> ""
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun tambahTemanBaru() {
        val antrian = Volley.newRequestQueue(this)
        val alamatUrl = "http://10.0.2.2/NMPProject/insert_friend.php"

        val stringRequest = object : StringRequest(Method.POST, alamatUrl,
            { respon ->
                val jsonRespon = JSONObject(respon)
                val jumlahTeman = jsonRespon.getString("total")

                AlertDialog.Builder(this)
                    .setTitle("Tambah Teman")
                    .setMessage("Berhasil menambah teman! Total teman Anda sekarang: $jumlahTeman")
                    .setPositiveButton("Selesai", null)
                    .show()

                binding.btnRequestFriend.isEnabled = false
            },
            { Toast.makeText(this, "Gagal memproses permintaan teman", Toast.LENGTH_SHORT).show() }
        ) {
            override fun getParams() = hashMapOf("nrp" to nrpMahasiswa)
        }
        antrian.add(stringRequest)
    }
}