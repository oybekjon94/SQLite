package com.example.sqlite01.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sqlite01.databinding.ItemStudentBinding
import com.example.sqlite01.models.Student

class StudentAdapter(val list:List<Student>):RecyclerView.Adapter<StudentAdapter.Vh>() {

    inner class Vh(var itemStudentBinding: ItemStudentBinding) :
        RecyclerView.ViewHolder(itemStudentBinding.root) {
            fun onBind(student: Student){
             itemStudentBinding.apply {
                 nameTv.text = student.name
             }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
      return Vh(ItemStudentBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size

}