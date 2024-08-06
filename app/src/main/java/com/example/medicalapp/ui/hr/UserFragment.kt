package com.example.medicalapp.ui.hr

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.medicalapp.R
import com.example.medicalapp.databinding.FragmentUserBinding
import com.example.medicalapp.util.ANALYSIS
import com.example.medicalapp.util.ANALYSIS_VIEW_KEY
import com.example.medicalapp.util.Resource
import com.example.medicalapp.util.SharedPrefs
import com.example.medicalapp.util.showToast
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@AndroidEntryPoint
class UserFragment : Fragment() {

    private val hrViewModel: HrViewModel by viewModels()
    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentUserBinding.bind(view)
        onClicks()
        observe()


    }

    private fun onClicks() {

        binding.apply {
            dateOfBirth.setOnClickListener {
                val datePicker =
                    MaterialDatePicker.Builder.datePicker()
                        .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                        .build()
                datePicker.show(parentFragmentManager, " material_date_picker")
                datePicker.addOnPositiveButtonClickListener {
                    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                    dateOfBirth.text = dateFormat.format(Date(it).time)
                }
            }
            btnBack.setOnClickListener {
                findNavController().navigateUp()
            }
            btnCreateUser.setOnClickListener {
                validation()
                findNavController().navigateUp()

            }
        }
    }

    private fun observe() {
        hrViewModel.mutableLiveData.observe(viewLifecycleOwner) { response ->
            when (response) {

                is Resource.Success -> {
                    response.data?.let { userData ->
                        showToast("Welcome Back ")
                        SharedPrefs.setUserToken(userData.access_token)
                        SharedPrefs.setUserName(userData.first_name + " " + userData.last_name)
                        SharedPrefs.setUserGender(userData.gender)
                        SharedPrefs.setUserType(userData.type)
                        SharedPrefs.setUserBirthDate(userData.birthday)
                        SharedPrefs.setUserStatus(userData.status)
                        SharedPrefs.setUserPhone(userData.mobile)
                        SharedPrefs.setUserEmail(userData.email)
                        SharedPrefs.setUserAdress(userData.address)
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


    private fun validation() {

        binding.apply {
            val fName = editFirstName.text.toString().trim()
            val lName = editLastName.text.toString().trim()
            val gender = gander.selectedItem.toString()
            var type = specialist.selectedItem.toString()
            val birthday = dateOfBirth.text.toString()
            val status2 = status.selectedItem.toString()
            val phone = editPhoneNumber.text.toString().trim()
            val email = editEmail.text.toString().trim()
            val address = editAdress.text.toString().trim()
            val password = editPassword.text.toString()

            if (fName == "") {
                editFirstName.error = getString(R.string.required)

            } else if (lName == "") {
                editLastName.error = getString(R.string.required)
            } else if (gander.selectedItemPosition == 0) {

                showToast(getString(R.string.please_select_gender))
            } else if (specialist.selectedItemPosition == 0) {
                showToast(getString(R.string.specialist_hint))
            } else if (birthday == "") {
                showToast(getString(R.string.birthday_hint))
            } else if (status.selectedItemPosition == 0) {
                showToast(getString(R.string.status_hint))
            } else if (phone == "") {
                editPhoneNumber.error = getString(R.string.required)
            } else if (address == "") {
                editAdress.error = getString(R.string.required)
            } else if (email == "") {
                editEmail.error = getString(R.string.required)
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.editEmail.error = getString(R.string.wrong_email_address)
            } else if (password == "") {
                editPassword.error = getString(R.string.required)
            } else {

                if (type == ANALYSIS_VIEW_KEY) {
                    type = ANALYSIS
                }
                hrViewModel.createNewUser(
                    email,
                    password,
                    fName,
                    lName,
                    gender,
                    type,
                    birthday,
                    status2,
                    address,
                    phone,
                    type
                )
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}