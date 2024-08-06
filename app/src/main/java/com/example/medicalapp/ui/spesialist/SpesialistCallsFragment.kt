//package com.example.medicalapp.spesialist
//
//import android.os.Bundle
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import com.example.medicalapp.R
//import com.example.medicalapp.databinding.FragmentCreateCallBinding
//
//
//class SpesialistCallsFragment : Fragment() {
//
//    var _binding : FragmentCreateCallBinding? = null
//    val binding get() = _binding!!
//    val viewModel:SpecialistViewModel by viewModels()
//    var docId = -1
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        _binding = FragmentCreateCallBinding.bind(view)
//
//        binding.btnCreateUser.setOnClickListener {
//            validation()
//        }
//
//        getDataOfDoc()
//
//
//
//        binding.txtSelectDoc.setOnClickListener {
//            findNavController().navigate(CreateCallFragmentDirections.actionCreateCallFragmentToSelectEmployeeFragment("Doctor"))
//        }
//        observe()
//    }
//
//
//    fun getDataOfDoc(){
//        setFragmentResultListener("employeeName") { _, bundle ->
//            val result = bundle.getString("dataName")
//            binding.txtSelectDoc.text = result
//        }
//        setFragmentResultListener("employeeId") { _, bundle ->
//            docId = bundle.getInt("dataId")
//        }
//    }
//
//    private fun observe() {
//        viewModel.mutableCreateCallLiveData.observe(viewLifecycleOwner){ response ->
//            when(response){
//                is Resource.Error -> showToast(response.message)
//                is Resource.Success -> goBack(response.data)
//            }
//        }
//    }
//
//    private fun goBack(message: String?) {
//        showToast(message)
//        findNavController().navigateUp()
//    }
//
//
//
//    private fun validation() {
//        val patientName = binding.edtPatientName.text.toString()
//        val patientAge = binding.edtAge.text.toString()
//        val patientPhone = binding.edtPhone.text.toString()
//        val patientDescription = binding.edtCaseDescription.text.toString()
//
//        if (patientName.isEmpty()) {
//            binding.edtPatientName.error = getString(R.string.required)
//        } else if (patientAge.isEmpty()) {
//            binding.edtAge.error = getString(R.string.required)
//        } else if (patientPhone.isEmpty()) {
//            binding.edtPhone.error = getString(R.string.required)
//        } else if (docId == -1) {
//            showToast("Select Doctor First")
//        } else if (patientDescription.isEmpty()) {
//            binding.edtCaseDescription.error = getString(R.string.required)
//        } else {
//            createCall(
//                patientName,
//                patientAge,
//                docId,
//                patientPhone,
//                patientDescription
//            )
//        }
//
//    }
//
//    private fun createCall(patientName: String, patientAge: String, docId: Int, patientPhone: String, patientDescription: String) {
//        viewModel.createCall(patientName , age = patientAge , phone = patientPhone ,
//            doctorId = docId, description = patientDescription)
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//
//    }
//
//}