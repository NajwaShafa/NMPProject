package com.example.miniprojectboleh

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import android.util.Log

class ViewPagerAdapter(activity: FragmentActivity)
    : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        Log.d("VP_ADAPTER", "getItemCount dipanggil")
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment()
            1 -> FriendsFragment()
            2 -> SettingsFragment()
            else -> HomeFragment()
        }
    }
}
