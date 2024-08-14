package com.example.medicalapp.ui.doctor.bottomSheet

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment.STYLE_NORMAL
import com.example.medicalapp.R
import com.example.medicalapp.databinding.FragmentBRequestfragmentBinding
import com.example.medicalapp.ui.base.BaseBottomSheet
import com.example.medicalapp.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BRequestfragment : BaseBottomSheet<FragmentBRequestfragmentBinding>(FragmentBRequestfragmentBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.AppBottomSheetDialogTheme)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}