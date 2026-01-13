package com.example.miniprojectboleh

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.miniprojectboleh.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // NIGHT MODE SWITCH
        binding.switchTheme.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_YES
                )
            } else {
                AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_NO
                )
            }
        }

        // RESET FRIENDS BUTTON
        binding.btnResetFriends.setOnClickListener {
            resetFriends()
        }
    }

    private fun resetFriends() {
        val url = "http://10.0.2.2/NMPProject/reset_friends.php"
        val queue = Volley.newRequestQueue(requireContext())

        val request = StringRequest(
            Request.Method.POST,
            url,
            {
                Toast.makeText(
                    context,
                    "My Friends berhasil di-reset",
                    Toast.LENGTH_SHORT
                ).show()
            },
            {
                Toast.makeText(
                    context,
                    "Gagal reset My Friends",
                    Toast.LENGTH_SHORT
                ).show()
            }
        )

        queue.add(request)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
