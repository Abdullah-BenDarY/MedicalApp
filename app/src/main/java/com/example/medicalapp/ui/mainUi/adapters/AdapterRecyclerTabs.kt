//package com.example.medicalapp.ui.mainUi.adapters
//
//import android.graphics.drawable.ColorDrawable
//import android.location.Address
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.AsyncListDiffer
//import androidx.recyclerview.widget.DiffUtil
//import androidx.recyclerview.widget.RecyclerView
//import androidx.recyclerview.widget.RecyclerView.ViewHolder
//import com.example.medicalapp.R
//import com.example.medicalapp.databinding.ItemTabsBinding
//
//class AdapterRecyclerTabs : RecyclerView.Adapter<AdapterRecyclerTabs.Holder>() {
//
//
//        inner class Holder(val binding: ItemTabsBinding) :
//            RecyclerView.ViewHolder(binding.root) {
//            fun bind(address: Address, isSelected: Boolean) {
//                binding.apply {
//                    textCategory.text = address.addressTitle
//                    if (isSelected) {
//                        textCategory.background =
//                            ColorDrawable(itemView.context.resources.getColor(R.color.mintGreen))
//                    } else {
//                        textCategory.background =
//                            ColorDrawable(itemView.context.resources.getColor(R.color.white))
//                    }
//                }
//            }
//
//        }
//
//
//        private val diffUtil = object : DiffUtil.ItemCallback<Address>() {
//            override fun areItemsTheSame(oldItem: Address, newItem: Address): Boolean {
//                return oldItem.addressTitle == newItem.addressTitle && oldItem.fullName == newItem.fullName
//            }
//
//            override fun areContentsTheSame(oldItem: Address, newItem: Address): Boolean {
//                return oldItem == newItem
//            }
//        }
//
//        val differ = AsyncListDiffer(this, diffUtil)
//
//        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
//            return Holder(
//                ItemTabsBinding.inflate(LayoutInflater.from(parent.context))
//            )
//        }
//
//        init {
//            differ.addListListener { _, _ ->
//                notifyItemChanged(selectedAddress)
//            }
//        }
//
//        override fun getItemCount(): Int {
//            return differ.currentList.size
//        }
//
//        var selectedAddress = -1
//        override fun onBindViewHolder(holder: Holder, position: Int) {
//            val address = differ.currentList[position]
//            holder.bind(address, selectedAddress == position)
//            holder.binding.textCategory.setOnClickListener {
//                if (selectedAddress <= 0) {
//                    notifyItemChanged(selectedAddress)
//                }
//                if (selectedAddress>0){
//                    notifyItemChanged(selectedAddress)
//                }
//                selectedAddress = holder.adapterPosition
//                notifyItemChanged(selectedAddress)
//                onClick?.invoke(address)
//
//            }
//        }
//
//        var onClick: ((Address) -> Unit)? = null
//
//
//    }