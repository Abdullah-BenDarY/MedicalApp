package com.example.medicalapp.ui.hr

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.medicalapp.adapters.AdapterRecyclerCategory
//import com.example.medicalapp.adapters.AdapterRecyclerCategory
import com.example.medicalapp.adapters.AdapterRecyclerEmployeeNames
import com.example.medicalapp.data.ModelCategory
import com.example.medicalapp.data.UsersData
import com.example.medicalapp.databinding.FragmentEmployeeBinding
import com.example.medicalapp.util.ALL
import com.example.medicalapp.util.ANALYSIS
import com.example.medicalapp.util.DOCTOR
import com.example.medicalapp.util.HR
import com.example.medicalapp.util.MANAGER
import com.example.medicalapp.util.NURSE
import com.example.medicalapp.util.RECEPTIONIST
import com.example.medicalapp.util.Resource
import com.example.medicalapp.util.showToast
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class EmployeeFragment  : Fragment() {
    private var _binding: FragmentEmployeeBinding? = null
    private val binding get() = _binding!!
    private var adapterCategory = AdapterRecyclerCategory()
    private var adapterEmployee = AdapterRecyclerEmployeeNames()
    val viewModel: HrViewModel by viewModels()
    var typeList = ArrayList<ModelCategory>()
    lateinit var usersData: List<UsersData>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEmployeeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClicks()
//        setCategoryAdapter()
//        setupCategoryAdapter()
        observe()
    }

    private fun observe() {
        viewModel.filterdList.observe(viewLifecycleOwner){response ->
            adapterEmployee.employeeData = response
            adapterEmployee.notifyDataSetChanged()
        }

        viewModel.getUsers(ALL)
        viewModel.mutableUsersLiveData.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    response.data?.let { data ->
                        usersData = data
                        setupAdapter()
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

    private fun onClicks() {
        binding.apply {
            recyclerEmployees.setOnClickListener {
                adapterEmployee.setOnClick {
                    findNavController()
                        .navigate(
                            EmployeeFragmentDirections.actionEmployeeFragmentToProfileFragment(it)
                        )
                }
            }
            btnAdd.setOnClickListener {
                findNavController().navigate(
                    EmployeeFragmentDirections.actionEmployeeFragmentToUserFragment()
                )
            }
            btnBack.setOnClickListener {
                findNavController().navigateUp()
            }
            searchBox.addTextChangedListener(object  : TextWatcher{
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }

                override fun afterTextChanged(s: Editable?) {
                    viewModel.filterText(s.toString())
                }
                })
            }
    }

//    private fun setupCategoryAdapter() {
//        val dataList = listOf("All", "Doctor", "Nurse" , "HR" , "Analysis")
//        adapterCategory = AdapterRecyclerCategory()
//        binding.recyclerTabs.adapter = adapterCategory
//    }

//    private fun setCategoryAdapter() {
//        typeList.add(ModelCategory(ALL))
//        typeList.add(ModelCategory(DOCTOR))
//        typeList.add(ModelCategory(HR))
//        typeList.add(ModelCategory(NURSE))
//        typeList.add(ModelCategory(RECEPTIONIST))
//        typeList.add(ModelCategory(MANAGER))
//        typeList.add(ModelCategory(ANALYSIS))
//        binding.recyclerTabs.adapter = adapterCategory
//        adapterCategory = AdapterRecyclerCategory()
//
//    }

    private fun setupAdapter() {
        val rv = binding.recyclerEmployees
        adapterEmployee = AdapterRecyclerEmployeeNames()
        adapterEmployee.employeeData = usersData
        rv.adapter = adapterEmployee
    }

        override fun onDestroy() {
            super.onDestroy()
            _binding = null
        }
}