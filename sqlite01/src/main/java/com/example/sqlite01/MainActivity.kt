package com.example.sqlite01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.example.sqlite01.adapter.StudentAdapter
import com.example.sqlite01.database.MyDbHelper
import com.example.sqlite01.databinding.ActivityMainBinding
import com.example.sqlite01.databinding.StudentDialogBinding
import com.example.sqlite01.models.Student

class MainActivity : AppCompatActivity() {

     lateinit var myDbHelper:MyDbHelper
    private val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding

    private lateinit var studentlist:ArrayList<Student>
    private lateinit var  studentAdapter : StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //object oladi
        myDbHelper = MyDbHelper(this)
        //studentlar ruyhatini olib studenta beradi
        studentlist = ArrayList(myDbHelper.listStudents())

        binding.apply {
            button.setOnClickListener{
                 val name = nameEt.text.toString()
                 val age = ageEt.text.toString().toInt()
                 val phone = phoneEt.text.toString()
                val student = Student(name = name, age = age, phoneNumber = phone)
                myDbHelper.addStudent(student)
                studentlist.add(student)
                studentAdapter.notifyItemInserted(studentlist.size)
            }
            studentAdapter = 
                StudentAdapter(studentlist,{student, position ->
                            myDbHelper.deleteStudent(student)
                            studentlist.remove(student)
                            studentAdapter.notifyItemRemoved(position)
                            studentAdapter.notifyItemChanged(position,studentlist.size)

                },{student, position ->
                    showEditingDialog(student,position)
                },{student ->
                    val intent = Intent(this@MainActivity,StudentActivity::class.java)
                    intent.putExtra("id",student.id)
                    startActivity(intent)


                })
            rv.adapter = studentAdapter

        }
    }

    private fun showEditingDialog(student: Student, position: Int) {
        val alertDialog = AlertDialog.Builder(this)
        val studentDialogBinding = StudentDialogBinding.inflate(layoutInflater)
        alertDialog.setView(studentDialogBinding.root)
        val dialog = alertDialog.create()
        studentDialogBinding.apply {
            nameEt.setText(student.name)
            ageEt.setText(student.age.toString())
            phoneEt.setText(student.phoneNumber)
            editBtn.setOnClickListener {
                val newName = nameEt.text.toString()
                val newAge = ageEt.text.toString().toInt()
                val newPhone = phoneEt.text.toString()
                student.name = newName
                student.age = newAge
                student.phoneNumber = newPhone
                myDbHelper.editStudent(student)
                studentAdapter.notifyItemChanged(position)
                dialog.dismiss()
            }
        }
        dialog.show()
    }

}