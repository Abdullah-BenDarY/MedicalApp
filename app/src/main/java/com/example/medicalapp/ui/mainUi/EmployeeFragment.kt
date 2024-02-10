package com.example.medicalapp.ui.mainUi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.medicalapp.R
import com.example.medicalapp.databinding.FragmentEmployeeBinding
import com.example.medicalapp.databinding.FragmentHrBinding

class EmployeeFragment : Fragment() {
    private var _binding: FragmentEmployeeBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_employee, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentEmployeeBinding.bind(view)

        binding.recyclerEmployees.setOnClickListener {
            findNavController().navigate(
                EmployeeFragmentDirections.actionEmployeeFragmentToProfileFragment()
            )
        }
        binding.btnAdd.setOnClickListener {
            findNavController().navigate(
                EmployeeFragmentDirections.actionEmployeeFragmentToUserFragment()
            )
        }
        binding.btnBack.setOnClickListener{
            findNavController().navigateUp()
        }
    }


}