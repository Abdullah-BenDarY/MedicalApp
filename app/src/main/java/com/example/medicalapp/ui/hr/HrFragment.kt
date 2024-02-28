package com.example.medicalapp.ui.hr

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.medicalapp.R
import com.example.medicalapp.databinding.FragmentHrBinding
import com.example.medicalapp.util.SharedPrefs


class HrFragment : Fragment() {
    private var _binding: FragmentHrBinding? = null
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_hr, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHrBinding.bind(view)

        setUpUi()
        navigation()


    }


    private fun setUpUi() {
        binding.textUsername.text = SharedPrefs.getUserName()
        binding.textUserdetails.text = SharedPrefs.getUserType()

    }

    private fun navigation() {
        binding.btnEmployee.setOnClickListener {
            findNavController()
                .navigate(HrFragmentDirections.actionHrFragmentToEmployeeFragment())
        }
        binding.imgUser.setOnClickListener {
            findNavController()
                .navigate(HrFragmentDirections.actionHrFragmentToProfileFragment(it.id))
        }
    }
}