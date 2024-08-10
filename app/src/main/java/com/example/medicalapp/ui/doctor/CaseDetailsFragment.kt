package com.example.medicalapp.ui.doctor

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.medicalapp.R
import com.example.medicalapp.databinding.FragmentCaseDetailsBinding
import com.example.medicalapp.ui.base.BaseFragment
import com.example.medicalapp.ui.doctor.adapters.AdapterDetailsTabs
import com.example.medicalapp.util.CASE
import com.example.medicalapp.util.MEDICAL_MEASUREMENT
import com.example.medicalapp.util.MEDICAL_RECORD
import com.example.medicalapp.util.Resource
import com.example.medicalapp.util.SharedPrefs
import com.example.medicalapp.util.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CaseDetailsFragment : BaseFragment<FragmentCaseDetailsBinding>(FragmentCaseDetailsBinding::inflate),
    AdapterDetailsTabs.OnItemClickListener {
    private lateinit var adapterTabs: AdapterDetailsTabs
    private val viewModel: DoctorViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val caseId = CaseDetailsFragmentArgs.fromBundle(requireArguments()).id
        SharedPrefs.setCaseId(caseId)
        adapterTabs = AdapterDetailsTabs(requireContext() ,this)
        setAdapter()
        observe()
        onClicks(caseId)
    }

    private fun observe() {
        viewModel.callStatusLiveData.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success ->
                    response.data?.let {
                        onEndCaseaClickActions()
                    }

                is Resource.Error ->
                    response.message?.let { message ->
                        showToast(message)
                    }

                null -> showToast("null")
            }
        }
    }

    private fun onClicks(caseId: Int) {
        binding.apply {
            btnBack.setOnClickListener {
                findNavController().navigateUp()
            }
            btnEndCase.setOnLongClickListener{
                viewModel.endCase(caseId)
                true
            }
        }
    }

    private fun onEndCaseaClickActions() {
    binding.apply {
        btnEndCase.isEnabled = false
        btnEndCase.backgroundTintList =
            ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.gray))
        btnEndCase.text = getString(R.string.case_ended)
        btnEndCase.setTextColor(ContextCompat.getColor(requireContext(), R.color.text_header))
    }
}

    private fun setAdapter() {
        binding.rvTabs.adapter = adapterTabs
    }

    override fun onItemClicked(item: String ) {
        val fragment = when (item) {
            CASE -> DetailsFragment()
            MEDICAL_RECORD -> MedicalRecordFragment()
            MEDICAL_MEASUREMENT -> MedicalMeasurementFragment()
            else -> null
        }
        fragment?.let {
            childFragmentManager.beginTransaction()
                .replace(R.id.frCaseDetails, it)
                .commit()
        }
    }
}
