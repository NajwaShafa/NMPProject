package com.example.miniprojectboleh

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.miniprojectboleh.databinding.ActivityListMhsBinding

class MhsAdapter() : RecyclerView.Adapter<MhsAdapter.MhsViewHolder>() {
    class MhsViewHolder(val binding: ActivityListMhsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MhsViewHolder {
        val binding = ActivityListMhsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MhsViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: MhsViewHolder,
        position: Int
    ) {
        holder.binding.txtName.text = DataMhs.mahasiswa[position].nama
        holder.binding.txtNrp.text = DataMhs.mahasiswa[position].nrp
        holder.binding.txtProdi.text = DataMhs.mahasiswa[position].prodi
        holder.binding.imgMhs.setImageResource(DataMhs.mahasiswa[position],imgId)
    }

    override fun getItemCount(): Int {
        return DataMhs.mahasiswa.size
    }
}