package com.example.medicalapp.ui.doctor.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.medicalapp.R.*
import com.example.medicalapp.databinding.ItemCaseDetailsTabsBinding
import com.example.medicalapp.util.CASE
import com.example.medicalapp.util.MEDICAL_MEASUREMENT
import com.example.medicalapp.util.MEDICAL_RECORD

class AdapterDetailsTabs(val context: Context, val listener: OnItemClickListener) : RecyclerView.Adapter<AdapterDetailsTabs.CallsHolder>() {

    private var casesData = listOf(CASE, MEDICAL_RECORD, MEDICAL_MEASUREMENT)
    private var selectedPosition = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CallsHolder {
        val binding = ItemCaseDetailsTabsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CallsHolder(binding)
    }

    override fun onBindViewHolder(holder: CallsHolder, position: Int) {
        holder.bind(casesData[position], position)
    }
    override fun getItemCount() = casesData.size

    inner class CallsHolder(val binding: ItemCaseDetailsTabsBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(tab: String, position: Int) {
            binding.apply {
                btnTabs.text = tab
                btnTabs.setOnClickListener {
                    if (selectedPosition == position) {
                        btnTabs.setBackgroundColor(ContextCompat.getColor(context, color.mintGreen))
                    } else {
                        btnTabs.setBackgroundDrawable(ContextCompat.getDrawable(context, drawable.tabs_shape))
                    }
                    notifyItemChanged(selectedPosition)
                    selectedPosition = position
                    notifyItemChanged(selectedPosition)
                    listener.onItemClicked(casesData[position])
                }
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClicked(item: String)
    }
}
