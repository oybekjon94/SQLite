package com.example.sqlite01.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sqlite01.databinding.ItemStudentBinding
import com.example.sqlite01.models.Student

class StudentAdapter(val list:List<Student>,
                     val onItemDelete:(Student,Int) -> Unit,
                     val onItemEdit:(Student,Int) -> Unit,
                     val onItemClick:(Student)-> Unit
):RecyclerView.Adapter<StudentAdapter.Vh>() {

    inner class Vh(var itemStudentBinding: ItemStudentBinding) :
        RecyclerView.ViewHolder(itemStudentBinding.root) {
            fun onBind(student: Student,position: Int){
             itemStudentBinding.apply {
                 nameTv.text = student.name
                 deleteBtn.setOnClickListener{onItemDelete.invoke(student,position)}
                 editBtn.setOnClickListener{onItemEdit.invoke(student,position)}
                 itemView.setOnClickListener{onItemClick.invoke(student)}
             }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
      return Vh(ItemStudentBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position],position)
    }

    override fun getItemCount(): Int = list.size



}