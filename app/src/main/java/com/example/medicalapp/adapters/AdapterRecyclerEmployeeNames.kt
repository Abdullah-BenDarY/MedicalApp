package com.example.medicalapp.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.medicalapp.R
import com.example.medicalapp.data.ModelCategory
import com.example.medicalapp.data.UsersData
import com.example.medicalapp.databinding.ItemEmployeeNamesBinding

class AdapterRecyclerEmployeeNames :
    RecyclerView.Adapter<AdapterRecyclerEmployeeNames.EmployeeHolder>() {


    var employeeData: List<UsersData>? = null
    private lateinit var onClick: (Int) -> Unit?
    fun setOnClick(onClick: (Int) -> Unit) {
        this.onClick = onClick
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeHolder {
        return EmployeeHolder(
            ItemEmployeeNamesBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun getItemCount(): Int {
        return employeeData?.size!!
    }


    override fun onBindViewHolder(holder: EmployeeHolder, position: Int) {
        val user = employeeData?.get(position)
        holder.bind(user)
    }

    inner class EmployeeHolder(val binding: ItemEmployeeNamesBinding) :
        ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                onClick.invoke(employeeData!![layoutPosition].id)
            }
        }
        fun bind(user: UsersData?) {
            binding.apply {
                textUsername.text = user?.first_name
                textUserdetails.text = user?.type
            }
        }
    }
}

