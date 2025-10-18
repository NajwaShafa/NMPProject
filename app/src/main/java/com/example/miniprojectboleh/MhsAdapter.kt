package com.example.miniprojectboleh

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.miniprojectboleh.databinding.ActivityListMhsBinding
import com.example.miniprojectboleh.databinding.ProfileMhsBinding

class MhsAdapter() : RecyclerView.Adapter<MhsAdapter.MhsViewHolder>() {
    class MhsViewHolder(val binding: ProfileMhsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MhsViewHolder {
        val binding = ProfileMhsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MhsViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: MhsViewHolder,
        position: Int
    ) {
        holder.binding.txtName.text = DataMhs.profile[position].nama
        holder.binding.txtNrp.text = DataMhs.profile[position].nrp
        holder.binding.txtProdi.text = DataMhs.profile[position].prodi
        holder.binding.imgMhs.setImageResource(DataMhs.profile[position].imgId)
    }

    override fun getItemCount(): Int {
        return DataMhs.profile.size
    }
}