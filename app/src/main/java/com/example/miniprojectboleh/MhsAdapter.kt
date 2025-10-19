package com.example.miniprojectboleh.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.miniprojectboleh.DetailMhs
import com.example.miniprojectboleh.ListMhs
import com.example.miniprojectboleh.Mahasiswa
import com.example.miniprojectboleh.databinding.ProfileMhsBinding

class MhsAdapter(private val data: Array<Mahasiswa>) :
    RecyclerView.Adapter<MhsAdapter.MhsViewHolder>() {

    inner class MhsViewHolder(val binding: ProfileMhsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(mhs: Mahasiswa, position: Int) {
            binding.txtName.text = mhs.nama
            binding.txtNrp.text = mhs.nrp
            binding.txtProdi.text = mhs.prodi
            binding.imgMhs.setImageResource(mhs.imgId)

            binding.root.setOnClickListener {
                val ctx = binding.root.context
                val intent = Intent(ctx, DetailMhs::class.java)
                intent.putExtra(ListMhs.MHS_INDEX, position)
                ctx.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MhsViewHolder {
        val binding = ProfileMhsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MhsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MhsViewHolder, position: Int) {
        val mhs = data[position]
        holder.bind(mhs, position)
    }

    override fun getItemCount(): Int = data.size
}
