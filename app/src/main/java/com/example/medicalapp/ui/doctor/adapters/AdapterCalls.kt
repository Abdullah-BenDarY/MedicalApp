package com.example.medicalapp.ui.doctor.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.medicalapp.data.CallsData
import com.example.medicalapp.databinding.ItemDoctorCallsBinding

class AdapterCalls : RecyclerView.Adapter<AdapterCalls.CallsHolder>() {
    var callsData: MutableList<CallsData>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CallsHolder {
        val binding = ItemDoctorCallsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CallsHolder(binding)
    }

    override fun getItemCount() = callsData?.size?: 0

    override fun onBindViewHolder(holder: CallsHolder, position: Int) {
        val calls = callsData?.get(position)
        if (calls != null) {
            holder.bind(calls)
        }
    }

    private var onAcceptClick: (Int) -> Unit = {}
    fun setOnAcceptClick(onAcceptClick: (Int) -> Unit) {
        this.onAcceptClick = onAcceptClick}

    private var onBusyClick: (Int) -> Unit = {}
    fun setOnBussyClick(onBusyClick: (Int) -> Unit) {
        this.onBusyClick = onBusyClick}

    fun deleteItem(position: Int) {
        if (position >= 0 && position < (callsData?.size ?: 0)) {
            callsData?.removeAt(position)
            notifyItemRemoved(position)
        } else {
            Log.e("AdapterCalls", "Invalid index $position, size: ${callsData?.size}")
        }
    }

    inner class CallsHolder( val binding: ItemDoctorCallsBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.btnAccept.setOnClickListener {
                onAcceptClick.invoke(callsData?.get(layoutPosition)!!.id)
                deleteItem(layoutPosition)
            }
            binding.btnBusy.setOnClickListener {
                onBusyClick.invoke(callsData?.get(layoutPosition)!!.id)
                deleteItem(layoutPosition)
            }
        }



            fun bind(calls: CallsData) {
                binding.apply {
                    tvUsername.text = calls.patient_name
                    tvDate.text = calls.created_at
                }
            }
        }
    }
