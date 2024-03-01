package com.example.medicalapp.adapters

import android.annotation.SuppressLint
import android.graphics.drawable.ColorDrawable
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.medicalapp.R
import com.example.medicalapp.data.ModelCategory
import com.example.medicalapp.databinding.ItemTabsBinding

class AdapterRecyclerCategory : RecyclerView.Adapter<AdapterRecyclerCategory.Holder>() {
    private var selectedPosition = -1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            ItemTabsBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
//
//        val data = differ.currentList[position]
//        holder.bind(data, selectedPosition == position)
//
//        if (selectedPosition > 0) {
//            notifyItemChanged(selectedPosition)
//        }
//        if (selectedPosition <= 0) {
//            notifyItemChanged(selectedPosition)
//        }
//        selectedPosition = holder.adapterPosition
//        notifyItemChanged(selectedPosition)
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

    private val diffutil = object : DiffUtil.ItemCallback<ModelCategory>() {

        override fun areItemsTheSame(oldItem: ModelCategory, newItem: ModelCategory): Boolean {
            return oldItem.title == newItem.title
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: ModelCategory, newItem: ModelCategory): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, diffutil)

    init {
        differ.addListListener { _, _ ->
            notifyItemChanged(selectedPosition)
        }
    }
}