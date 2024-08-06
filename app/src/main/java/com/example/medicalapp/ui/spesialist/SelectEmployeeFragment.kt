//package com.example.medicalapp.spesialist
//
//import android.os.Bundle
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.core.os.bundleOf
//import androidx.fragment.app.setFragmentResult
//import androidx.fragment.app.viewModels
//import androidx.navigation.fragment.findNavController
//import com.example.medicalapp.R
////import com.example.medicalapp.adapters.AdapterRecyclerSelectEmployee
//import com.example.medicalapp.databinding.FragmentSelectEmployeeBinding
//import com.example.medicalapp.ui.hr.HrViewModel
//import com.example.medicalapp.util.Resource
//import com.example.medicalapp.util.showToast
//
//
//class SelectEmployeeFragment : Fragment() {
//
//
//    private var _binding : FragmentSelectEmployeeBinding?=null
//    private val binding get() = _binding!!
//    lateinit var employeeType :String
//    private val adapterRecyclerSelectEmployee:
//            AdapterRecyclerSelectEmployee by lazy { AdapterRecyclerSelectEmployee() }
//    private var employeeId = 0
//    private var employeeName = ""
//    private val hrViewModel : HrViewModel by viewModels()
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        _binding = FragmentSelectEmployeeBinding.bind(view)
//        employeeType = SelectEmployeeFragmentArgs.fromBundle(requireArguments()).employeeType
//
//        setTextToUI(employeeType)
//        hrViewModel.getUsers(employeeType)
//        observe()
//        onClicks()
//
//    }
//
//    private fun onClicks() {
//        adapterRecyclerSelectEmployee.onUserClick = object : AdapterRecyclerSelectEmployee.OnUserClick{
//            override fun onClick(id: Int, name: String) {
//                employeeId = id
//                employeeName = name
//            }
//        }
//
//        binding.apply {
//            btnBack.setOnClickListener{
//                findNavController().navigateUp()
//            }
//            btnSelectEmployee.setOnClickListener {
//                if (employeeId == 0){
//                    //showToast("you must select $employeeType")
//                }else{
//
//                    setFragmentResult("employeeId", bundleOf("dataId" to employeeId))
//                    setFragmentResult("employeeName", bundleOf("dataName" to employeeName))
//                    findNavController().navigateUp()
//                }
//            }
//        }
//    }
//
//    private fun observe() {
//        hrViewModel.mutableUsersLiveData.observe(viewLifecycleOwner){ response->
//            when(response){
//                is Resource.Error -> showToast(response.message)
//                is Resource.Success -> {
//                    // adapterRecyclerSelectEmployee.rowIndex = -1
//                    adapterRecyclerSelectEmployee.list = response.data
//                    binding.rvEmployee.adapter = adapterRecyclerSelectEmployee
//                    binding.lottie.visibility = View.GONE
//                }
//            }
//        }
//    }
//
//
//
//    private fun setTextToUI(type: String) {
//        binding.apply {
//            textView4.text = "Select $type"
//            editText4.hint = "Search for $type"
//            btnSelectEmployee.text = "Select $type"
//        }
//    }
//    override fun onDestroy() {
//        super.onDestroy()
//        _binding = null
//    }
//
//
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
