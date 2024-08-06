package com.example.medicalapp.adapters


import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.medicalapp.R
import com.example.medicalapp.data.ModelCategory

import com.example.medicalapp.databinding.ItemTabsBinding

class AdapterRecyclerCategory : RecyclerView.Adapter<AdapterRecyclerCategory.Holder>() {

    var employeeData: List<ModelCategory>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            ItemTabsBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun getItemCount(): Int {
        return employeeData?.size!!

    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val user = employeeData?.get(position)
        if (user != null) {
            holder.bind(user, position == -1)
        }

    }
    inner class Holder(val binding: ItemTabsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ModelCategory, isSelected: Boolean) {
            binding.apply {
                textCategory.text = data.title
                if (isSelected) {
                    textCategory.background =
                        ColorDrawable(ContextCompat.getColor(itemView.context, R.color.mintGreen))
                    textCategory.setTextColor(
                        ContextCompat.getColor(
                            itemView.context,
                            R.color.mintGreen
                        )
                    )
                }
                else {
                    textCategory.setBackgroundResource(R.drawable.tabs_shape)
                    textCategory.setTextColor(
                        ContextCompat.getColor(
                            itemView.context,
                            R.color.black
                        )
                    )
                }
            }
        }
    }
}