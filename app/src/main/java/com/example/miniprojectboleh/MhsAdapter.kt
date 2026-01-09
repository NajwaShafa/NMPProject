package com.example.miniprojectboleh.adapter

import Mahasiswa
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.miniprojectboleh.DetailMhs
import com.example.miniprojectboleh.R
import com.example.miniprojectboleh.databinding.ProfileMhsBinding

class MhsAdapter(private val data: ArrayList<Mahasiswa>) :
    RecyclerView.Adapter<MhsAdapter.MhsViewHolder>() {

    inner class MhsViewHolder(val binding: ProfileMhsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(mhs: Mahasiswa) {
            binding.txtName.text = mhs.nama
            binding.txtNrp.text = mhs.nrp
            binding.txtProdi.text = mhs.program


            binding.imgMhs.setImageResource(R.mipmap.ic_launcher)

            binding.root.setOnClickListener {
                val ctx = binding.root.context
                val intent = Intent(ctx, DetailMhs::class.java)
                intent.putExtra("nrp", mhs.nrp)
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

    override fun getItemCount(): Int = data.size
}
