package com.example.medicalapp.ui.doctor

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.medicalapp.R
import com.example.medicalapp.data.CallsData
import com.example.medicalapp.data.DoctotorCasesData
import com.example.medicalapp.databinding.FragmentDoctorCallsBinding
import com.example.medicalapp.databinding.FragmentDoctorCasesBinding
import com.example.medicalapp.ui.base.BaseFragment
import com.example.medicalapp.ui.doctor.adapters.AdapterCalls
import com.example.medicalapp.ui.doctor.adapters.AdapterDoctorCases
import com.example.medicalapp.util.ACCEPT
import com.example.medicalapp.util.REJECT
import com.example.medicalapp.util.Resource
import com.example.medicalapp.util.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DoctorCasesFragment  : BaseFragment<FragmentDoctorCasesBinding>(FragmentDoctorCasesBinding::inflate) {

    private val adapterCases = AdapterDoctorCases()
    private val viewModel: DoctorViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllCases()
        observeCalls()
        onClicks()
    }

    override fun onClicks() {
        binding.apply {
            btnBack.setOnClickListener {
                findNavController().navigateUp()}
            adapterCases.setOnDetailsClick {
//                findNavController().navigate(R.id.action_doctorCasesFragment_to_taskDetailsFragment)
            }
        }
    }

    private fun observeCalls() {
        viewModel.doctorCassesLiveData.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success ->
                    response.data?.let {
                        setupAdapter(it)
                    }

                is Resource.Error ->
                    response.message?.let { message ->
                        showToast(message)
                    }
            }
        }
    }


    @SuppressLint("NotifyDataSetChanged")
    private fun setupAdapter(callsList: List<DoctotorCasesData>) {
        adapterCases.casesData = callsList
        binding.rvCalls.adapter = adapterCases
        adapterCases.notifyDataSetChanged()
    }
}