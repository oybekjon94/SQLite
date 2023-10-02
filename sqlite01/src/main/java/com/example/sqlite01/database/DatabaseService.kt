package com.example.sqlite01.database

import com.example.sqlite01.models.Student

interface DatabaseService {
    //bu yerda argumentga object xam kere boladi
    //buning uchun class ochamiz
    fun addStudent(student: Student)

    //list korinishidagi studentlar jacvali create qilish kere boladi
    fun listStudents():List<Student>

    fun editStudent(student: Student)

    fun deleteStudent(student: Student)

    fun getStudentsCount():Int

    //id aloqador studentni qaytarib bersin
    fun getStudentById(id:Int):Student
}