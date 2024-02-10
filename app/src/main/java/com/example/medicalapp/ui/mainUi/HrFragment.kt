package com.example.medicalapp.ui.mainUi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.medicalapp.R
import com.example.medicalapp.databinding.FragmentHrBinding
import com.example.medicalapp.databinding.FragmentLoginBinding


class HrFragment : Fragment() {
    private var _binding: FragmentHrBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hr, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHrBinding.bind(view)

        binding.imgUser.setOnClickListener {
            findNavController().navigate(
                HrFragmentDirections.actionHrFragmentToProfileFragment()
            )
        }
        binding.btnEmployee.setOnClickListener {
            findNavController().navigate(
                HrFragmentDirections.actionHrFragmentToEmployeeFragment()
            )
        }


        }
    }