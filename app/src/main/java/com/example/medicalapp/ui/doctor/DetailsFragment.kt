package com.example.medicalapp.ui.doctor

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.medicalapp.R
import com.example.medicalapp.databinding.FragmentDetailsBinding
import com.example.medicalapp.ui.base.BaseFragment
import com.example.medicalapp.util.SharedPrefs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment() : BaseFragment<FragmentDetailsBinding>(FragmentDetailsBinding::inflate) {
private val caseId = SharedPrefs.getCaseId()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}