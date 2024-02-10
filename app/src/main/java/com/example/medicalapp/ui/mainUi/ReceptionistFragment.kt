package com.example.medicalapp.ui.mainUi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.medicalapp.R
import com.example.medicalapp.databinding.FragmentSpecialistBinding


class ReceptionistFragment : Fragment() {
    private var _binding: FragmentSpecialistBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_receptionist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSpecialistBinding.bind(view)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}