package com.example.medicalapp.ui.doctor.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.medicalapp.data.DoctotorCasesData
import com.example.medicalapp.databinding.ItemDoctorCasesBinding

class AdapterDoctorCases : RecyclerView.Adapter<AdapterDoctorCases.CallsHolder>() {
    var casesData: List<DoctotorCasesData>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CallsHolder {
        val binding = ItemDoctorCasesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CallsHolder(binding)
    }

    override fun getItemCount() = casesData?.size?: 0

    override fun onBindViewHolder(holder: CallsHolder, position: Int) {
        val cases = casesData?.get(position)
        if (cases != null) {
            holder.bind(cases)
        }
    }


    private var onDetailsClick: (Int) -> Unit = {}
    fun setOnDetailsClick(onDetailsClick: (Int) -> Unit) {
        this.onDetailsClick = onDetailsClick}



    inner class CallsHolder( val binding: ItemDoctorCasesBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.btnShowDetails.setOnClickListener {
                onDetailsClick.invoke(casesData?.get(layoutPosition)!!.id)
            }
        }
            fun bind(calls: DoctotorCasesData?) {
                binding.apply {
                    tvUsername.text = calls!!.patient_name
                    tvDate.text = calls.created_at
                }
            }
        }
    }
