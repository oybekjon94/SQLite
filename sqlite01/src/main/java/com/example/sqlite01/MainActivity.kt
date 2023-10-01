package com.example.sqlite01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.sqlite01.adapter.StudentAdapter
import com.example.sqlite01.database.MyDbHelper
import com.example.sqlite01.databinding.ActivityMainBinding
import com.example.sqlite01.models.Student

class MainActivity : AppCompatActivity() {

     lateinit var myDbHelper:MyDbHelper
    private val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding

    private lateinit var studentlist:ArrayList<Student>
    private val studentAdapter by lazy {
        StudentAdapter(studentlist)
    }

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
            rv.adapter = studentAdapter
        }
    }

}