package com.example.medicalapp.ui.doctor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.medicalapp.databinding.FragmentDoctorBinding
import com.example.medicalapp.ui.base.BaseFragment
import com.example.medicalapp.util.SharedPrefs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DoctorFragment : BaseFragment<FragmentDoctorBinding>(FragmentDoctorBinding::inflate) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)


    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onClicks()
        setupUi()
    }



    override fun onClicks() {
        binding.apply {
            btnCalls.setOnClickListener {
                findNavController().navigate(
                    DoctorFragmentDirections.actionDoctorFragmentToCallsFragment()
                )
            }
        }

    }


    private fun setupUi(){
        binding.apply {
            textUsername.text = SharedPrefs.getUserName()
            textUserdetails.text = SharedPrefs.getUserType()
        }
    }
}