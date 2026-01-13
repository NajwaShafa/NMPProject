package com.example.miniprojectboleh.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.miniprojectboleh.DetailMhs
import com.example.miniprojectboleh.Mahasiswa
import com.example.miniprojectboleh.databinding.ProfileMhsBinding
import com.squareup.picasso.Picasso
import android.util.Log
import android.view.View

class MhsAdapter(private val data: ArrayList<Mahasiswa>) :
    RecyclerView.Adapter<MhsAdapter.MhsViewHolder>() {
    inner class MhsViewHolder(val binding: ProfileMhsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(mhs: Mahasiswa) {
            binding.txtName.text = mhs.nama
            binding.txtNrp.text = mhs.nrp
            binding.txtProdi.text = mhs.program

            //Load foto menggunakan nama variabel yang sinkron dengan database
            if (mhs.photo_url.isNotEmpty()) {
                Picasso.get()
                    .load(mhs.photo_url)
                    .into(binding.imgMhs)
            }
            binding.btnEmail.visibility = View.GONE

            binding.root.setOnClickListener {
                val ctx = binding.root.context
                val intent = Intent(ctx, DetailMhs::class.java)
                intent.putExtra("NRP", mhs.nrp)
                intent.putExtra("PHOTO_URL", mhs.photo_url) // ⬅️ TAMBAH INI
                ctx.startActivity(intent)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MhsViewHolder {
        val binding =
            ProfileMhsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MhsViewHolder(binding)
    }
    override fun onBindViewHolder(holder: MhsViewHolder, position: Int) {
        holder.bind(data[position])
    }
    override fun getItemCount(): Int {
        Log.d("ADAPTER", "itemCount = ${data.size}")
        return data.size
    }

}