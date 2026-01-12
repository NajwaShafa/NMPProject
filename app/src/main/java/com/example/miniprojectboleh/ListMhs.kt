package com.example.miniprojectboleh

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.miniprojectboleh.databinding.ActivityListMhsBinding // Sesuaikan nama binding layout Anda

class ListMhs : AppCompatActivity() {
    private lateinit var binding: ActivityListMhsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListMhsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Setup ViewPager2 dengan Adapter
        val adapter = ViewPagerAdapter(this)
        binding.viewPager.adapter = adapter

        // Sinkronisasi ViewPager dengan Bottom Navigation
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.bottomNav.menu.getItem(position).isChecked = true
            }
        })

        //Klik Bottom Menu untuk mengubah halaman ViewPager
        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.itemHome -> binding.viewPager.currentItem = 0
                R.id.itemFriends -> binding.viewPager.currentItem = 1
                R.id.itemSettings -> binding.viewPager.currentItem = 2
            }
            true
        }
    }
}