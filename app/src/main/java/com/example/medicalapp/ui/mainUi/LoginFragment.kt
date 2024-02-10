package com.example.medicalapp.ui.mainUi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.medicalapp.R
import com.example.medicalapp.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentLoginBinding.bind(view)
        binding.btnLogin.setOnClickListener {
            chekFields()
        }
    }

    private fun chekFields() {
        if (binding.editPhone.text!!.isEmpty() || binding.editPassword.text!!.isEmpty()) {
            Toast.makeText(
                requireContext(),
                getString(R.string.login_error_message),
                Toast.LENGTH_LONG
            ).show()
        } else {
            findNavController().navigate(
                LoginFragmentDirections.actionLoginFragmentToHrFragment()
            )
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}