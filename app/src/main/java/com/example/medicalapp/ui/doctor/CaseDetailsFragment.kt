package com.example.medicalapp.ui.doctor

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.medicalapp.databinding.FragmentCaseDetailsBinding
import com.example.medicalapp.ui.base.BaseFragment
import com.example.medicalapp.ui.doctor.adapters.AdapterCalls
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CaseDetailsFragment : BaseFragment<FragmentCaseDetailsBinding>(FragmentCaseDetailsBinding::inflate) {

    private val adapterCalls = AdapterCalls()
    private val viewModel: DoctorViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllCalls()
        observeCalls()
        onClicks()
    }

    override fun onClicks() {

    }

    private fun observeCalls() {

    }
}