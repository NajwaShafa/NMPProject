package com.example.miniprojectboleh.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.miniprojectboleh.Mahasiswa
import com.example.miniprojectboleh.databinding.ProfileMhsBinding
import com.squareup.picasso.Picasso

class FriendAdapter(
    private val context: Context,
    private val data: ArrayList<Mahasiswa>
) : RecyclerView.Adapter<FriendAdapter.FriendViewHolder>() {

    inner class FriendViewHolder(val binding: ProfileMhsBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendViewHolder {
        val binding = ProfileMhsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FriendViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FriendViewHolder, position: Int) {
        val mhs = data[position]

        holder.binding.txtName.text = mhs.nama
        holder.binding.txtNrp.text = mhs.nrp
        holder.binding.txtProdi.text = mhs.program

        if (mhs.photo_url.isNotEmpty()) {
            Picasso.get()
                .load(mhs.photo_url)
                .into(holder.binding.imgMhs)
        }

        holder.binding.btnEmail.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:${mhs.email}") // WAJIB mailto
                putExtra(Intent.EXTRA_SUBJECT, "Hello ${mhs.nama}")
                putExtra(Intent.EXTRA_TEXT, "Halo ${mhs.nama}, apa kabar?")
            }

            context.startActivity(
                Intent.createChooser(intent, "Kirim email dengan")
            )
        }
    }

    override fun getItemCount(): Int = data.size
}
