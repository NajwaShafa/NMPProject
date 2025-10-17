package com.example.miniprojectboleh

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MhsAdapter() : RecyclerView.Adapter<MhsAdapter.MhsViewHolder>() {
    class MhsViewHolder(val binding: ListMhsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MhsViewHolder {
        val binding = ListMhsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MhsViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: MhsViewHolder,
        position: Int
    ) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}