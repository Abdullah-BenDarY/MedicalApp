package com.example.medicalapp.ui.doctor

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.medicalapp.R
import com.example.medicalapp.data.CallsData
import com.example.medicalapp.databinding.FragmentDoctorCallsBinding
import com.example.medicalapp.ui.base.BaseFragment
import com.example.medicalapp.ui.doctor.adapters.AdapterCalls
import com.example.medicalapp.util.ACCEPT
import com.example.medicalapp.util.REJECT
import com.example.medicalapp.util.Resource
import com.example.medicalapp.util.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CallsFragment : BaseFragment<FragmentDoctorCallsBinding>(FragmentDoctorCallsBinding::inflate) {

    private val adapterCalls = AdapterCalls()
    private val viewModel: DoctorViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllCalls()
        observeCalls()
        observeCallStatus()
        onClicks()
    }
    override fun onClicks() {
        binding.apply {
            btnBack.setOnClickListener {
                findNavController().navigateUp()}
            adapterCalls.setOnAcceptClick {
                viewModel.getCAllResponce(status = ACCEPT , id = it)
            }
            adapterCalls.setOnBussyClick {
                viewModel.getCAllResponce(status = REJECT , id = it)
            }
        }
    }
    private fun observeCalls() {
        viewModel.mutableLiveData.observe(viewLifecycleOwner) { response ->
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
    private fun observeCallStatus() {
        viewModel.callStatusLiveData.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success ->
                    showToast(getString(R.string.good_luck))
                is Resource.Error ->
                    response.message?.let { message ->
                        showToast(message)
                    }
                null ->showToast(response?.message)
            }
        }
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun setupAdapter(callsList: MutableList<CallsData>) {
        adapterCalls.callsData = callsList
        binding.rvCalls.adapter = adapterCalls
        adapterCalls.notifyDataSetChanged()
    }
}