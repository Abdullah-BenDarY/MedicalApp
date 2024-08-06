package com.example.medicalapp.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.medicalapp.R
import com.example.medicalapp.databinding.FragmentLoginBinding
import com.example.medicalapp.util.DOCTOR
import com.example.medicalapp.util.HR
import com.example.medicalapp.util.RECEPTIONIST
import com.example.medicalapp.util.REQUIRED
import com.example.medicalapp.util.Resource
import com.example.medicalapp.util.SharedPrefs
import com.example.medicalapp.util.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentLoginBinding.bind(view)
        observe()
        onClick()
    }

    private fun onClick() {

        binding.apply {
            btnLogin.setOnClickListener {
                val email = editEmail.text.toString().trim()
                val password = editPassword.text.toString().trim()
                if (validateLogin(email, password)) {
                    viewModel.login(email, password, "")
                }
            }
        }
    }


    private fun observe() {
        viewModel.mutableLiveData.observe(viewLifecycleOwner) { response ->
            when (response) {

                is Resource.Success -> {
                    response.data?.let { userData ->
                        showToast(getString(R.string.welcome_back))
                        SharedPrefs.setUserToken(userData.access_token)
                        SharedPrefs.setUserType(userData.type)
                        SharedPrefs.setUserName(userData.first_name + " " + userData.last_name)
                        navigate(userData.type)
                    }
                }

                is Resource.Error -> {
                    response.message?.let { message ->
                        showToast(message)
                    }
                }
            }
        }
    }


    private fun navigate(type: String) {
        if (type == HR) {
            findNavController().navigate(
                LoginFragmentDirections.actionLoginFragmentToHrFragment())
        } else if (type == RECEPTIONIST) {
            findNavController().navigate(
                LoginFragmentDirections.actionLoginFragmentToReceptionistFragment())
        }else if (type == DOCTOR) {
            findNavController().navigate(
                LoginFragmentDirections.actionLoginFragmentToDoctorFragment())
        } else{
        }
    }


    private fun validateLogin(email: String, password: String): Boolean {
        val emailRegex = Regex("^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,})+$")
        if (email.isEmpty()) {
            binding.editEmail.error = REQUIRED
            binding.editEmail.requestFocus()
            return false
        } else if (password.isEmpty()) {
            binding.editPassword.error = REQUIRED
            binding.editPassword.requestFocus()
            return false
        } else if (!emailRegex.matches(email)) {
            binding.editEmail.error = getString(R.string.wrong_email)
            binding.editEmail.requestFocus()
            return false
        } else {
            return true
        }

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}

