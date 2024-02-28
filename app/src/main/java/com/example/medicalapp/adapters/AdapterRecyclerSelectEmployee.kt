//package com.example.medicalapp.adapters
//
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import com.example.medicalapp.R
//import com.example.medicalapp.data.UsersData
//
//class AdapterRecyclerSelectEmployee : RecyclerView.Adapter<AdapterRecyclerSelectEmployee.Holder>()  {
//
//    var list : List<UsersData> ?= null
//    var onUserClick : OnUserClick ?= null
//    var rowIndex  = -1
//    inner class Holder (view : View) : RecyclerView.ViewHolder(view){
//
//        val textType = view.findViewById<TextView>(R.id.text_type)
//        val textName = view.findViewById<TextView>(R.id.text_user_name)
//        val imageSelect = view.findViewById<ImageView>(R.id.image_select)
//
//        init {
//            itemView.setOnClickListener {
//                rowIndex = layoutPosition
//                onUserClick?.onClick(
//                    list?.get(layoutPosition)?.id!!,
//                    list?.get(layoutPosition)?.first_name!! )
//
//                notifyDataSetChanged()
//            }
//        }
//
//    }
//
//    interface OnUserClick {
//        fun onClick (id : Int , name :String)
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
//        val view = LayoutInflater.from(parent.context)
//            .inflate(R.layout.item_employee, parent , false)
//
//        return Holder(view)
//    }
//
//    override fun getItemCount(): Int {
//        return list?.size ?: 0
//    }
//
//    override fun onBindViewHolder(holder: Holder, position: Int) {
//        val data = list?.get(position)
//
//        holder.apply {
//
//            holder.textName.text = data?.first_name
//            holder.textType.text = data?.type
//
//            if (rowIndex == position){
//                holder.imageSelect.setImageResource(R.drawable.ic_radio_selected)
//            }else{
//                holder.imageSelect.setImageResource(R.drawable.ic_radio_un_selected)
//            }
//        }
//    }
//}