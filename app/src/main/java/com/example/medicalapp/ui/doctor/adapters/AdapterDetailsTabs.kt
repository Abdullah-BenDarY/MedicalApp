package com.example.medicalapp.ui.doctor.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.medicalapp.data.Tabs
import com.example.medicalapp.databinding.ItemTabsBinding

class AdapterDetailsTabs : RecyclerView.Adapter<AdapterDetailsTabs.CallsHolder>() {
    var casesData: List<Tabs>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CallsHolder {
        val binding = ItemTabsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CallsHolder(binding)
    }

    override fun getItemCount() = casesData?.size?: 0

    override fun onBindViewHolder(holder: CallsHolder, position: Int) {
        val cases = casesData?.get(position)
        if (cases != null) {
            holder.bind(cases)
        }
    }


    private var onTabsClick: (String) -> Unit = {}
    fun setOnTabsClick(onDetailsClick: (String) -> Unit) {
        this.onTabsClick = onDetailsClick}



    inner class CallsHolder( val binding: ItemTabsBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onTabsClick.invoke(casesData?.get(layoutPosition)!!.tabName)
            }

        }
        fun bind(tabs: Tabs?) {
            binding.apply {
               textCategory.text = tabs!!.tabName

            }
        }
    }
}
