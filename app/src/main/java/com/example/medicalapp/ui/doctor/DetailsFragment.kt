package com.example.medicalapp.ui.doctor

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.medicalapp.data.CaseDetails
import com.example.medicalapp.databinding.FragmentDetailsBinding
import com.example.medicalapp.ui.base.BaseFragment
import com.example.medicalapp.util.PROCESS
import com.example.medicalapp.util.Resource
import com.example.medicalapp.util.SharedPrefs
import com.example.medicalapp.util.showToast
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.internal.notify

@AndroidEntryPoint
class DetailsFragment : BaseFragment<FragmentDetailsBinding>(FragmentDetailsBinding::inflate) {
    private val viewModel: DoctorViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val caseId = SharedPrefs.getCaseId()
        viewModel.getCaseDetails(caseId)
        observeCaseDetails()
        onClicks()
    }

    private fun observeCaseDetails() {
        viewModel.caseDetailsLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    setupUi(it.data!!)
                }

                is Resource.Error -> {
                    showToast(it.message)
                }
            }
        }
    }

    override fun onClicks() {
        super.onClicks()
        binding.apply {
            btnAddNurce.setOnClickListener{
                showToast("Hello Nurse ;) :D")
            }
            btnRequest.setOnClickListener {
                findNavController()
                    .navigate(DetailsFragmentDirections.actionDetailsFragmentToBRequestfragment())
            }

        }

    }
    private fun setupUi(data: CaseDetails) {
        binding.apply {
            tvPatientName.text = data.patient_name
            tvPatientAge.text = data.age
            tvPatientNumber.text = data.phone
            tvPatientDate.text = data.created_at
            tvPatientDoctor.text = data.doctor_id
            tvPatientNurse.text = data.nurse_id
            tvPatientStatus.text = data.case_status
            tvPatientDescription.text = data.description
            if (tvPatientStatus.text == PROCESS) icStatus.visibility = View.VISIBLE
            else icStatus.visibility = View.GONE
            if(tvPatientNurse.text!=null) btnAddNurce.isEnabled = false
       }
    }
}