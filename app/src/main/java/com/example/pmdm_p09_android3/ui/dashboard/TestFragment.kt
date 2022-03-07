package com.example.pmdm_p09_android3.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.pmdm_p09_android3.BodyActivity
import com.example.pmdm_p09_android3.Data.DataDbHelper
import com.example.pmdm_p09_android3.databinding.FragmentTestBinding
import com.example.pmdm_p09_android3.models.Users


class TestFragment : Fragment() {

    private var _binding: FragmentTestBinding? = null
    lateinit var educodeDbHelper: DataDbHelper
    private lateinit var user: Users
    private var quest = 1

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(TestViewModel::class.java)

        _binding = FragmentTestBinding.inflate(inflater, container, false)
        val root: View = binding.root

        educodeDbHelper = (activity as? BodyActivity)?.getDbHelper()!!
        user = (activity as? BodyActivity)?.getLogUser()!!
        var question: Array<String> = educodeDbHelper.getQuestion(quest)

        binding.textTestQuestion.setText(question[0])
        binding.rdbtnOpt1.setText(question[1])
        binding.rdbtnOpt2.setText(question[2])
        binding.rdbtnOpt3.setText(question[3])

        binding.btnNext.setOnClickListener {
            quest++
            if(quest < 5) {
                val selectedId: Int = binding.radioGroup.getCheckedRadioButtonId()

                val radioButton: RadioButton = requireActivity().findViewById(selectedId)
                if(radioButton.text.toString().equals(question[4])){
                    user.success++
                } else {
                    user.error++
                }

                if(quest < 4) {
                    question = educodeDbHelper.getQuestion(quest)
                    binding.textTestQuestion.setText(question[0])
                    binding.rdbtnOpt1.setText(question[1])
                    binding.rdbtnOpt2.setText(question[2])
                    binding.rdbtnOpt3.setText(question[3])
                } else {
                    binding.btnNext.text = "Finalizar"
                }

            } else {

                if(quest > 4){
                    educodeDbHelper.updateResults(user.email, user.success, user.error)
                    user.success = 0
                    user.error = 0
                    binding.testConstraintLayout.visibility = View.INVISIBLE
                }
            }
        }


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onPause() {
        super.onPause()
        quest = 1
    }
}