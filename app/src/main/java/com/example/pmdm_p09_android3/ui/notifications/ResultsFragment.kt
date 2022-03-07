package com.example.pmdm_p09_android3.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.pmdm_p09_android3.BodyActivity
import com.example.pmdm_p09_android3.Data.DataDbHelper
import com.example.pmdm_p09_android3.MainActivity
import com.example.pmdm_p09_android3.R
import com.example.pmdm_p09_android3.databinding.FragmentResultsBinding
import com.example.pmdm_p09_android3.databinding.FragmentTestBinding
import com.example.pmdm_p09_android3.models.Users

class ResultsFragment : Fragment() {

    private var _binding: FragmentResultsBinding? = null
    lateinit var educodeDbHelper: DataDbHelper
    private lateinit var user: Users

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(ResultsViewModel::class.java)

        _binding = FragmentResultsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        user = (activity as? BodyActivity)?.getLogUser()!!
        educodeDbHelper = (activity as? BodyActivity)?.getDbHelper()!!

        binding.textResultUser.setText(user.email)

        val results: IntArray = educodeDbHelper.getResult(user.email)

        binding.textSuccessesVal.setText(results[0].toString())
        binding.textErrorsVal.setText(results[1].toString())

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}