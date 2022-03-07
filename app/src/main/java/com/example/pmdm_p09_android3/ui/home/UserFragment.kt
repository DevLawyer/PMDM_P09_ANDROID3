package com.example.pmdm_p09_android3.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.pmdm_p09_android3.BodyActivity
import com.example.pmdm_p09_android3.Data.DataDbHelper
import com.example.pmdm_p09_android3.MainActivity
import com.example.pmdm_p09_android3.databinding.FragmentUserBinding
import com.example.pmdm_p09_android3.models.Users

class UserFragment : Fragment() {

    private var _binding: FragmentUserBinding? = null
    lateinit var educodeDbHelper: DataDbHelper
    private var user: Users? = null


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(UserViewModel::class.java)

        _binding = FragmentUserBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.btnUserClose.setOnClickListener {
            user = null
            activity?.finish()
        }


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onStart() {
        user = (activity as? BodyActivity)?.getLogUser()!!
        educodeDbHelper = (activity as? BodyActivity)?.getDbHelper()!!
        super.onStart()
    }

    override fun onResume() {
        binding.textUserName.setText(user?.user )
        binding.textUserEmail.setText(user?.email)
        binding.textUserPass.setText(user?.pass)
        super.onResume()
    }

}